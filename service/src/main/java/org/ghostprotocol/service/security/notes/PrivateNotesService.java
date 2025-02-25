package org.ghostprotocol.service.security.notes;

import org.ghostprotocol.service.storage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Service for managing private notes in the vault system.
 * Provides methods for creating, retrieving, updating, and deleting notes,
 * as well as filtering notes by category, tag, and pinned status.
 */
public class PrivateNotesService {
    private static final Logger logger = LoggerFactory.getLogger(PrivateNotesService.class);
    
    private final VaultStorageService vaultStorageService;
    private final VaultEncryptionService encryptionService;
    private final VaultKeyManager keyManager;
    private final Clock clock;

    @Inject
    public PrivateNotesService(
            VaultStorageService vaultStorageService,
            VaultEncryptionService encryptionService,
            VaultKeyManager keyManager,
            Clock clock) {
        this.vaultStorageService = vaultStorageService;
        this.encryptionService = encryptionService;
        this.keyManager = keyManager;
        this.clock = clock;
    }

    /**
     * Creates a new note in the vault.
     *
     * @param account  The account creating the note
     * @param title    The title of the note
     * @param content  The content of the note
     * @param category The category of the note
     * @param tags     The tags associated with the note
     * @param isPinned Whether the note is pinned
     * @return The UUID of the created note
     */
    public UUID createNote(Account account, String title, String content, String category, List<String> tags, boolean isPinned) {
        try {
            UUID noteId = UUID.randomUUID();
            UUID userId = account.getUuid();
            
            // Create note data object
            NoteData noteData = new NoteData(
                content,
                category,
                tags,
                isPinned
            );
            
            // Serialize and encrypt note data
            String serializedData = noteData.serialize();
            byte[] key = keyManager.deriveItemKey(userId, noteId.toString());
            String encryptedContent = encryptionService.encrypt(serializedData, key);
            
            // Create vault item
            NoteVaultItem noteItem = new NoteVaultItem(
                noteId,
                userId,
                encryptedContent.getBytes(),
                title,
                encryptedContent.getBytes().length,
                Instant.now(clock)
            );
            
            // Store in vault
            vaultStorageService.storeItem(userId, noteItem);
            
            return noteId;
        } catch (Exception e) {
            logger.error("Failed to create note", e);
            throw new RuntimeException("Failed to create note", e);
        }
    }

    /**
     * Retrieves a note from the vault.
     *
     * @param account The account retrieving the note
     * @param noteId  The UUID of the note to retrieve
     * @return An Optional containing the note if found, or empty if not found
     */
    public Optional<NoteVaultItem> getNote(Account account, UUID noteId) {
        try {
            UUID userId = account.getUuid();
            
            CompletableFuture<Optional<VaultItem>> future = vaultStorageService.getItem(userId, noteId);
            Optional<VaultItem> optionalItem = future.join();
            
            if (!optionalItem.isPresent()) {
                return Optional.empty();
            }
            
            VaultItem item = optionalItem.get();
            if (!(item instanceof NoteVaultItem)) {
                logger.error("Item is not a note: {}", noteId);
                return Optional.empty();
            }
            
            return Optional.of((NoteVaultItem) item);
        } catch (Exception e) {
            logger.error("Failed to get note", e);
            return Optional.empty();
        }
    }

    /**
     * Retrieves the decrypted data of a note.
     *
     * @param account The account retrieving the note data
     * @param noteId  The UUID of the note to retrieve data for
     * @return The decrypted note data
     * @throws RuntimeException if the note is not found or decryption fails
     */
    public NoteData getNoteData(Account account, UUID noteId) {
        try {
            Optional<NoteVaultItem> optionalNote = getNote(account, noteId);
            if (!optionalNote.isPresent()) {
                throw new IllegalStateException("Note not found: " + noteId);
            }
            
            NoteVaultItem noteItem = optionalNote.get();
            byte[] key = keyManager.deriveItemKey(account.getUuid(), noteId.toString());
            String decryptedContent = encryptionService.decrypt(new String(noteItem.getEncryptedData()), key);
            
            return NoteData.deserialize(decryptedContent);
        } catch (Exception e) {
            logger.error("Failed to get note data", e);
            throw new RuntimeException("Failed to get note data", e);
        }
    }

    /**
     * Updates an existing note in the vault.
     *
     * @param account  The account updating the note
     * @param noteId   The UUID of the note to update
     * @param title    The new title of the note
     * @param content  The new content of the note
     * @param category The new category of the note
     * @param tags     The new tags associated with the note
     * @param isPinned Whether the note is pinned
     * @throws RuntimeException if the note is not found or update fails
     */
    public void updateNote(Account account, UUID noteId, String title, String content, String category, List<String> tags, boolean isPinned) {
        try {
            UUID userId = account.getUuid();
            
            // Get existing note to verify it exists
            Optional<NoteVaultItem> optionalNote = getNote(account, noteId);
            if (!optionalNote.isPresent()) {
                throw new IllegalStateException("Note not found: " + noteId);
            }
            
            // Create updated note data
            NoteData noteData = new NoteData(
                content,
                category,
                tags,
                isPinned
            );
            
            // Serialize and encrypt note data
            String serializedData = noteData.serialize();
            byte[] key = keyManager.deriveItemKey(userId, noteId.toString());
            String encryptedContent = encryptionService.encrypt(serializedData, key);
            
            // Create updated vault item
            NoteVaultItem updatedNoteItem = new NoteVaultItem(
                noteId,
                userId,
                encryptedContent.getBytes(),
                title,
                encryptedContent.getBytes().length,
                Instant.now(clock)
            );
            
            // Store in vault
            vaultStorageService.storeItem(userId, updatedNoteItem);
        } catch (Exception e) {
            logger.error("Failed to update note", e);
            throw new RuntimeException("Failed to update note", e);
        }
    }

    /**
     * Deletes a note from the vault.
     *
     * @param account The account deleting the note
     * @param noteId  The UUID of the note to delete
     * @throws RuntimeException if deletion fails
     */
    public void deleteNote(Account account, UUID noteId) {
        try {
            UUID userId = account.getUuid();
            vaultStorageService.deleteItem(userId, noteId);
        } catch (Exception e) {
            logger.error("Failed to delete note", e);
            throw new RuntimeException("Failed to delete note", e);
        }
    }

    /**
     * Retrieves all notes in a specific category.
     *
     * @param account  The account retrieving the notes
     * @param category The category to filter by
     * @return A list of notes in the specified category
     */
    public List<NoteVaultItem> getNotesByCategory(Account account, String category) {
        try {
            UUID userId = account.getUuid();
            
            CompletableFuture<List<VaultItem>> future = vaultStorageService.listItems(userId);
            List<VaultItem> allItems = future.join();
            
            List<NoteVaultItem> notes = allItems.stream()
                .filter(item -> item.getType().equals(VaultItemType.NOTE.name()))
                .map(item -> (NoteVaultItem) item)
                .collect(Collectors.toList());
            
            // Filter by category
            return notes.stream()
                .filter(note -> {
                    try {
                        NoteData noteData = getNoteData(account, note.getId());
                        return category.equals(noteData.getCategory());
                    } catch (Exception e) {
                        logger.error("Failed to get note data for filtering by category", e);
                        return false;
                    }
                })
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to get notes by category", e);
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves all notes with a specific tag.
     *
     * @param account The account retrieving the notes
     * @param tag     The tag to filter by
     * @return A list of notes with the specified tag
     */
    public List<NoteVaultItem> getNotesByTag(Account account, String tag) {
        try {
            UUID userId = account.getUuid();
            
            CompletableFuture<List<VaultItem>> future = vaultStorageService.listItems(userId);
            List<VaultItem> allItems = future.join();
            
            List<NoteVaultItem> notes = allItems.stream()
                .filter(item -> item.getType().equals(VaultItemType.NOTE.name()))
                .map(item -> (NoteVaultItem) item)
                .collect(Collectors.toList());
            
            // Filter by tag
            return notes.stream()
                .filter(note -> {
                    try {
                        NoteData noteData = getNoteData(account, note.getId());
                        return noteData.getTags().contains(tag);
                    } catch (Exception e) {
                        logger.error("Failed to get note data for filtering by tag", e);
                        return false;
                    }
                })
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to get notes by tag", e);
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves all pinned notes.
     *
     * @param account The account retrieving the notes
     * @return A list of pinned notes
     */
    public List<NoteVaultItem> getPinnedNotes(Account account) {
        try {
            UUID userId = account.getUuid();
            
            CompletableFuture<List<VaultItem>> future = vaultStorageService.listItems(userId);
            List<VaultItem> allItems = future.join();
            
            List<NoteVaultItem> notes = allItems.stream()
                .filter(item -> item.getType().equals(VaultItemType.NOTE.name()))
                .map(item -> (NoteVaultItem) item)
                .collect(Collectors.toList());
            
            // Filter by pinned status
            return notes.stream()
                .filter(note -> {
                    try {
                        NoteData noteData = getNoteData(account, note.getId());
                        return noteData.isPinned();
                    } catch (Exception e) {
                        logger.error("Failed to get note data for filtering by pinned status", e);
                        return false;
                    }
                })
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to get pinned notes", e);
            return Collections.emptyList();
        }
    }
}
