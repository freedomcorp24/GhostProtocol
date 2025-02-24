package org.ghostprotocol.service.security.notes;

import org.ghostprotocol.service.crypto.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PrivateNotesService {
    private static final Logger logger = LoggerFactory.getLogger(PrivateNotesService.class);
    private final CryptoService cryptoService;
    private final Map<String, Map<String, EncryptedNote>> userNotes = new ConcurrentHashMap<>();

    public PrivateNotesService(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    public String createNote(String userId, String content, List<String> tags) {
        String noteId = UUID.randomUUID().toString();
        String encryptedContent = cryptoService.encrypt(content);
        
        EncryptedNote note = new EncryptedNote(
            noteId,
            encryptedContent,
            System.currentTimeMillis(),
            tags
        );

        userNotes.computeIfAbsent(userId, k -> new ConcurrentHashMap<>())
                 .put(noteId, note);

        return noteId;
    }

    public String getNoteContent(String userId, String noteId) {
        EncryptedNote note = getUserNote(userId, noteId);
        return cryptoService.decrypt(note.encryptedContent);
    }

    public void updateNote(String userId, String noteId, String content) {
        EncryptedNote note = getUserNote(userId, noteId);
        String encryptedContent = cryptoService.encrypt(content);
        
        EncryptedNote updatedNote = new EncryptedNote(
            noteId,
            encryptedContent,
            System.currentTimeMillis(),
            note.tags
        );

        userNotes.get(userId).put(noteId, updatedNote);
    }

    public void deleteNote(String userId, String noteId) {
        Map<String, EncryptedNote> notes = userNotes.get(userId);
        if (notes != null) {
            notes.remove(noteId);
        }
    }

    private EncryptedNote getUserNote(String userId, String noteId) {
        Map<String, EncryptedNote> notes = userNotes.get(userId);
        if (notes == null) {
            throw new IllegalStateException("No notes found for user: " + userId);
        }

        EncryptedNote note = notes.get(noteId);
        if (note == null) {
            throw new IllegalStateException("Note not found: " + noteId);
        }

        return note;
    }

    private static class EncryptedNote {
        private final String id;
        private final String encryptedContent;
        private final long timestamp;
        private final List<String> tags;

        public EncryptedNote(String id, String encryptedContent, long timestamp, List<String> tags) {
            this.id = id;
            this.encryptedContent = encryptedContent;
            this.timestamp = timestamp;
            this.tags = tags;
        }
    }
}
