package org.ghostprotocol.service.security;

import org.ghostprotocol.service.storage.VaultStorageManager;
import org.ghostprotocol.service.storage.MessagesManager;
import org.ghostprotocol.service.storage.DynamoDbTable;
import org.ghostprotocol.service.util.UUIDUtil;

import java.security.SecureRandom;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DuressService {
    private static final int MAX_MESSAGE_LENGTH = 500;
    private static final String DEFAULT_DURESS_MESSAGE = 
        "This account has been compromised and all data has been securely wiped.";
    private static final List<String> DECOY_MESSAGES = Arrays.asList(
        "Hey, how are you?",
        "What's up?",
        "Did you see that new movie?",
        "Want to grab lunch tomorrow?",
        "Thanks for the help!",
        "Can't make it today, sorry!",
        "See you later!",
        "Have a great weekend!",
        "Let me know when you're free",
        "Got the files, thanks!"
    );
    private static final List<String> DECOY_NAMES = Arrays.asList(
        "John Smith", "Emma Wilson", "Michael Brown", "Sarah Davis",
        "David Miller", "Lisa Anderson", "James Taylor", "Jennifer White",
        "Robert Johnson", "Mary Williams", "Daniel Jones", "Patricia Moore",
        "Christopher Lee"
    );

    private final DynamoDbTable<DuressConfig> duressConfigTable;
    private final DynamoDbTable<DecoyContact> decoyContactTable;
    private final DynamoDbTable<DecoyMessage> decoyMessageTable;
    private final MessagesManager messagesManager;
    private final VaultStorageManager vaultStorageManager;
    private final SecureRandom random;
    private final Clock clock;

    public DuressService(
            DynamoDbTable<DuressConfig> duressConfigTable,
            DynamoDbTable<DecoyContact> decoyContactTable,
            DynamoDbTable<DecoyMessage> decoyMessageTable,
            MessagesManager messagesManager,
            VaultStorageManager vaultStorageManager,
            Clock clock) {
        this.duressConfigTable = duressConfigTable;
        this.decoyContactTable = decoyContactTable;
        this.decoyMessageTable = decoyMessageTable;
        this.messagesManager = messagesManager;
        this.vaultStorageManager = vaultStorageManager;
        this.random = new SecureRandom();
        this.clock = clock;
    }

    public CompletableFuture<Void> configureDuress(
            UUID userId, 
            String duressPassword,
            String customMessage,
            UUID emergencyContact) {
        if (customMessage != null && customMessage.length() > MAX_MESSAGE_LENGTH) {
            return CompletableFuture.failedFuture(
                new IllegalArgumentException("Message exceeds maximum length"));
        }

        DuressConfig config = new DuressConfig(
            userId,
            duressPassword,
            customMessage,
            emergencyContact
        );

        return CompletableFuture.runAsync(() -> duressConfigTable.put(config));
    }

    public CompletableFuture<Void> activateDuress(UUID userId, String duressPassword) {
        return CompletableFuture.supplyAsync(() -> 
            duressConfigTable.get(userId.toString())
        ).thenCompose(config -> {
            if (config == null || !config.getDuressPassword().equals(duressPassword)) {
                return CompletableFuture.completedFuture(null); // Silent fail for security
            }

            // Send emergency message if configured
            CompletableFuture<Void> notifyEmergencyContact = 
                Optional.ofNullable(config.getEmergencyContact())
                    .map(contact -> sendEmergencyMessage(contact, 
                        config.getCustomMessage() != null ? 
                        config.getCustomMessage() : DEFAULT_DURESS_MESSAGE))
                    .orElse(CompletableFuture.completedFuture(null));

            // Wipe all data
            CompletableFuture<Void> wipeData = CompletableFuture.allOf(
                messagesManager.clear(userId),
                vaultStorageManager.deleteAllForUser(userId)
            );

            // Generate and store decoy data
            return CompletableFuture.allOf(notifyEmergencyContact, wipeData)
                .thenCompose(v -> generateDecoyData(userId));
        });
    }

    private CompletableFuture<Void> sendEmergencyMessage(UUID contactId, String message) {
        return CompletableFuture.runAsync(() ->
            messagesManager.sendEmergencyMessage(contactId, message)
        );
    }

    private CompletableFuture<Void> generateDecoyData(UUID userId) {
        // Generate random number of contacts (3-13)
        int numContacts = 3 + random.nextInt(11);
        
        // Create shuffled lists of names to pick from
        List<String> availableNames = new ArrayList<>(DECOY_NAMES);
        Collections.shuffle(availableNames);

        // Generate decoy contacts
        List<DecoyContact> contacts = IntStream.range(0, numContacts)
            .mapToObj(i -> new DecoyContact(
                UUID.randomUUID(),
                availableNames.get(i % availableNames.size()),
                generatePhoneNumber()
            ))
            .collect(Collectors.toList());

        // Store contacts
        CompletableFuture<Void> storeContacts = CompletableFuture.allOf(
            contacts.stream()
                .map(contact -> CompletableFuture.runAsync(() ->
                    decoyContactTable.put(contact)))
                .toArray(CompletableFuture[]::new)
        );

        // Generate and store messages for each contact
        return storeContacts.thenCompose(v -> {
            List<CompletableFuture<Void>> messagesFutures = new ArrayList<>();
            
            for (DecoyContact contact : contacts) {
                int numMessages = 1 + random.nextInt(9);
                List<String> availableMessages = new ArrayList<>(DECOY_MESSAGES);
                Collections.shuffle(availableMessages);

                for (int i = 0; i < numMessages; i++) {
                    DecoyMessage message = new DecoyMessage(
                        UUID.randomUUID(),
                        userId,
                        contact.getId(),
                        availableMessages.get(i % availableMessages.size()),
                        random.nextBoolean(),
                        clock.instant().minusSeconds(random.nextInt(7 * 24 * 60 * 60)) // Random time in last week
                    );

                    messagesFutures.add(CompletableFuture.runAsync(() ->
                        decoyMessageTable.put(message)));
                }
            }

            return CompletableFuture.allOf(
                messagesFutures.toArray(new CompletableFuture[0]));
        });
    }

    private String generatePhoneNumber() {
        StringBuilder number = new StringBuilder("+1");
        for (int i = 0; i < 10; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
}
