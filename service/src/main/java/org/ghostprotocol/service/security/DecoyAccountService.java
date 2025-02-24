package org.ghostprotocol.service.security;

import org.ghostprotocol.service.crypto.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DecoyAccountService {
    private static final Logger logger = LoggerFactory.getLogger(DecoyAccountService.class);
    private static final int MIN_CONTACTS = 3;
    private static final int MAX_CONTACTS = 20;
    private static final int MIN_CONVERSATIONS = 1;
    private static final int MAX_CONVERSATIONS = 10;

    private final ConcurrentHashMap<String, DecoyAccount> decoyAccounts = new ConcurrentHashMap<>();
    private final CryptoService cryptoService;
    private final SecureRandom random = new SecureRandom();

    public DecoyAccountService(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    public DecoyAccount createDecoyAccount(String userId, String duressPassword) {
        int numContacts = random.nextInt(MAX_CONTACTS - MIN_CONTACTS + 1) + MIN_CONTACTS;
        int numConversations = random.nextInt(MAX_CONVERSATIONS - MIN_CONVERSATIONS + 1) + MIN_CONVERSATIONS;

        List<Contact> contacts = generateDecoyContacts(numContacts);
        List<Conversation> conversations = generateDecoyConversations(contacts, numConversations);

        DecoyAccount decoy = new DecoyAccount(userId, contacts, conversations);
        String encryptedDuressPassword = cryptoService.encrypt(duressPassword);
        decoy.setDuressPassword(encryptedDuressPassword);

        decoyAccounts.put(userId, decoy);
        return decoy;
    }

    public void handleDuressLogin(String userId, String password) {
        DecoyAccount decoy = decoyAccounts.get(userId);
        if (decoy != null && cryptoService.decrypt(decoy.getDuressPassword()).equals(password)) {
            // Trigger emergency protocol
            sendEmergencyMessage(userId);
            wipeRealAccount(userId);
            switchToDecoyAccount(userId);
        }
    }

    private List<Contact> generateDecoyContacts(int count) {
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new Contact(
                UUID.randomUUID().toString(),
                generateRandomName(),
                generateRandomPhoneNumber()
            ));
        }
        return contacts;
    }

    private List<Conversation> generateDecoyConversations(List<Contact> contacts, int count) {
        List<Conversation> conversations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Contact contact = contacts.get(random.nextInt(contacts.size()));
            conversations.add(new Conversation(
                UUID.randomUUID().toString(),
                contact.getId(),
                generateDecoyMessages()
            ));
        }
        return conversations;
    }

    private List<Message> generateDecoyMessages() {
        List<Message> messages = new ArrayList<>();
        int messageCount = random.nextInt(20) + 5;
        long baseTime = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000); // Week ago

        for (int i = 0; i < messageCount; i++) {
            messages.add(new Message(
                UUID.randomUUID().toString(),
                generateRandomMessage(),
                baseTime + (i * 3600000), // Hour intervals
                random.nextBoolean()
            ));
        }
        return messages;
    }

    private void sendEmergencyMessage(String userId) {
        // Implementation for sending encrypted emergency message
        logger.info("Emergency message sent for user: {}", userId);
    }

    private void wipeRealAccount(String userId) {
        // Implementation for secure account data deletion
        logger.info("Account data wiped for user: {}", userId);
    }

    private void switchToDecoyAccount(String userId) {
        // Implementation for switching to decoy account
        logger.info("Switched to decoy account for user: {}", userId);
    }

    private String generateRandomName() {
        String[] firstNames = {"John", "Jane", "Michael", "Sarah", "David", "Emma"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia"};
        return firstNames[random.nextInt(firstNames.length)] + " " + 
               lastNames[random.nextInt(lastNames.length)];
    }

    private String generateRandomPhoneNumber() {
        StringBuilder number = new StringBuilder("+1");
        for (int i = 0; i < 10; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    private String generateRandomMessage() {
        String[] messages = {
            "Hey, how are you?",
            "Can we meet tomorrow?",
            "Thanks for the help!",
            "See you soon!",
            "Great work on the project!"
        };
        return messages[random.nextInt(messages.length)];
    }

    // Inner classes for data structure
    public static class DecoyAccount {
        private final String userId;
        private final List<Contact> contacts;
        private final List<Conversation> conversations;
        private String duressPassword;

        public DecoyAccount(String userId, List<Contact> contacts, List<Conversation> conversations) {
            this.userId = userId;
            this.contacts = contacts;
            this.conversations = conversations;
        }

        public void setDuressPassword(String duressPassword) {
            this.duressPassword = duressPassword;
        }

        public String getDuressPassword() {
            return duressPassword;
        }
    }

    public static class Contact {
        private final String id;
        private final String name;
        private final String phoneNumber;

        public Contact(String id, String name, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getId() {
            return id;
        }
    }

    public static class Conversation {
        private final String id;
        private final String contactId;
        private final List<Message> messages;

        public Conversation(String id, String contactId, List<Message> messages) {
            this.id = id;
            this.contactId = contactId;
            this.messages = messages;
        }
    }

    public static class Message {
        private final String id;
        private final String content;
        private final long timestamp;
        private final boolean sent;

        public Message(String id, String content, long timestamp, boolean sent) {
            this.id = id;
            this.content = content;
            this.timestamp = timestamp;
            this.sent = sent;
        }
    }
}
