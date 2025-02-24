package org.ghostprotocol.service.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DecoyDataGenerator {
    private static final Random random = new Random();
    
    private static final String[] COMMON_MESSAGES = {
        "Hey, how are you?",
        "What's up?",
        "Want to grab lunch?",
        "Are you free this weekend?",
        "Did you see that news?",
        "Thanks!",
        "Sure, no problem",
        "I'll get back to you later",
        "Can't make it today",
        "See you tomorrow!"
    };

    public static List<DecoyContact> generateContacts() {
        List<DecoyContact> contacts = new ArrayList<>();
        int numContacts = random.nextInt(5) + 3; // Generate 3-7 contacts
        
        for (int i = 0; i < numContacts; i++) {
            contacts.add(new DecoyContact(
                UUID.randomUUID(),
                generateUsername(),
                generateProfileName(),
                System.currentTimeMillis() - random.nextInt(30) * 86400000L // Random date within last 30 days
            ));
        }
        
        return contacts;
    }

    public static List<DecoyMessage> generateMessages(DecoyContact contact) {
        List<DecoyMessage> messages = new ArrayList<>();
        int numMessages = random.nextInt(20) + 10; // Generate 10-30 messages
        
        long lastTimestamp = System.currentTimeMillis();
        for (int i = 0; i < numMessages; i++) {
            // Generate random timestamp between contact creation and now
            long timestamp = contact.getCreatedAt() + 
                           random.nextInt((int)(lastTimestamp - contact.getCreatedAt()));
            
            boolean incoming = random.nextBoolean();
            messages.add(new DecoyMessage(
                UUID.randomUUID(),
                contact.getUuid(),
                incoming ? contact.getUuid() : null,
                incoming ? null : contact.getUuid(),
                generateMessageContent(),
                timestamp
            ));
        }
        
        return messages;
    }

    private static String generateUsername() {
        String[] prefixes = {"user", "person", "friend", "contact"};
        return prefixes[random.nextInt(prefixes.length)] + random.nextInt(10000);
    }

    private static String generateProfileName() {
        String[] firstNames = {"James", "John", "Robert", "Michael", "William", "David", "Mary", "Patricia", "Jennifer", "Linda"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
        
        return firstNames[random.nextInt(firstNames.length)] + " " + 
               lastNames[random.nextInt(lastNames.length)];
    }

    private static String generateMessageContent() {
        if (random.nextDouble() < 0.7) { // 70% chance of using common message
            return COMMON_MESSAGES[random.nextInt(COMMON_MESSAGES.length)];
        } else { // 30% chance of generating random message
            int words = random.nextInt(5) + 1;
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < words; i++) {
                message.append(generateWord()).append(" ");
            }
            return message.toString().trim();
        }
    }

    private static String generateWord() {
        String[] words = {"the", "be", "to", "of", "and", "a", "in", "that", "have", "I",
                         "it", "for", "not", "on", "with", "he", "as", "you", "do", "at"};
        return words[random.nextInt(words.length)];
    }
}
