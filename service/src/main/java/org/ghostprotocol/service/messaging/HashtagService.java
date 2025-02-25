package org.ghostprotocol.service.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Singleton
public class HashtagService {
    private static final Logger logger = LoggerFactory.getLogger(HashtagService.class);
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#(\\w+)");
    
    // Maps hashtags to message IDs
    private final Map<String, Set<UUID>> hashtagMessages = new ConcurrentHashMap<>();
    
    // Maps message IDs to hashtags
    private final Map<UUID, Set<String>> messageHashtags = new ConcurrentHashMap<>();
    
    // Maps group IDs to hashtags
    private final Map<UUID, Set<String>> groupHashtags = new ConcurrentHashMap<>();
    
    // Maps group ID + hashtag to message IDs
    private final Map<String, Set<UUID>> groupHashtagMessages = new ConcurrentHashMap<>();

    @Inject
    public HashtagService() {}

    public CompletableFuture<Set<String>> extractHashtags(String message) {
        try {
            Set<String> hashtags = new HashSet<>();
            if (message == null || message.isEmpty()) {
                return CompletableFuture.completedFuture(hashtags);
            }
            
            Matcher matcher = HASHTAG_PATTERN.matcher(message);
            while (matcher.find()) {
                String hashtag = matcher.group(1).toLowerCase();
                hashtags.add(hashtag);
            }
            
            logger.debug("Extracted {} hashtags from message", hashtags.size());
            return CompletableFuture.completedFuture(hashtags);
        } catch (Exception e) {
            logger.error("Error extracting hashtags", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Void> indexHashtags(UUID messageId, Set<String> hashtags) {
        try {
            if (messageId == null || hashtags == null || hashtags.isEmpty()) {
                return CompletableFuture.completedFuture(null);
            }
            
            // Store hashtags for this message
            messageHashtags.put(messageId, new HashSet<>(hashtags));
            
            // Update global hashtag index
            for (String hashtag : hashtags) {
                hashtagMessages.computeIfAbsent(hashtag, k -> ConcurrentHashMap.newKeySet())
                              .add(messageId);
            }
            
            logger.debug("Indexed {} hashtags for message {}", hashtags.size(), messageId);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            logger.error("Error indexing hashtags", e);
            return CompletableFuture.failedFuture(e);
        }
    }
    
    public CompletableFuture<Void> indexHashtagsForGroup(UUID messageId, UUID groupId, Set<String> hashtags) {
        try {
            if (messageId == null || groupId == null || hashtags == null || hashtags.isEmpty()) {
                return CompletableFuture.completedFuture(null);
            }
            
            // Update group hashtags
            groupHashtags.computeIfAbsent(groupId, k -> ConcurrentHashMap.newKeySet())
                        .addAll(hashtags);
            
            // Update group hashtag message index
            for (String hashtag : hashtags) {
                String key = groupId.toString() + ":" + hashtag;
                groupHashtagMessages.computeIfAbsent(key, k -> ConcurrentHashMap.newKeySet())
                                   .add(messageId);
            }
            
            // Also update the global indices
            return indexHashtags(messageId, hashtags);
        } catch (Exception e) {
            logger.error("Error indexing hashtags for group", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Set<UUID>> findMessagesByHashtag(String hashtag, UUID groupId) {
        try {
            if (hashtag == null || hashtag.isEmpty()) {
                return CompletableFuture.completedFuture(Collections.emptySet());
            }
            
            // Remove # prefix if present
            if (hashtag.startsWith("#")) {
                hashtag = hashtag.substring(1);
            }
            
            hashtag = hashtag.toLowerCase();
            
            if (groupId != null) {
                // Find messages with hashtag in specific group
                String key = groupId.toString() + ":" + hashtag;
                Set<UUID> messages = groupHashtagMessages.getOrDefault(key, Collections.emptySet());
                return CompletableFuture.completedFuture(new HashSet<>(messages));
            } else {
                // Find all messages with hashtag
                Set<UUID> messages = hashtagMessages.getOrDefault(hashtag, Collections.emptySet());
                return CompletableFuture.completedFuture(new HashSet<>(messages));
            }
        } catch (Exception e) {
            logger.error("Error finding messages by hashtag", e);
            return CompletableFuture.failedFuture(e);
        }
    }
    
    public CompletableFuture<Set<String>> getHashtagsInGroup(UUID groupId) {
        try {
            if (groupId == null) {
                return CompletableFuture.completedFuture(Collections.emptySet());
            }
            
            Set<String> hashtags = groupHashtags.getOrDefault(groupId, Collections.emptySet());
            return CompletableFuture.completedFuture(new HashSet<>(hashtags));
        } catch (Exception e) {
            logger.error("Error getting hashtags in group", e);
            return CompletableFuture.failedFuture(e);
        }
    }
    
    public CompletableFuture<List<String>> getTrendingHashtags(int limit) {
        try {
            List<String> trending = hashtagMessages.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()))
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            
            return CompletableFuture.completedFuture(trending);
        } catch (Exception e) {
            logger.error("Error getting trending hashtags", e);
            return CompletableFuture.failedFuture(e);
        }
    }
    
    public CompletableFuture<Void> removeMessage(UUID messageId) {
        try {
            if (messageId == null) {
                return CompletableFuture.completedFuture(null);
            }
            
            Set<String> hashtags = messageHashtags.remove(messageId);
            if (hashtags != null) {
                // Remove message from hashtag indices
                for (String hashtag : hashtags) {
                    Set<UUID> messages = hashtagMessages.get(hashtag);
                    if (messages != null) {
                        messages.remove(messageId);
                        if (messages.isEmpty()) {
                            hashtagMessages.remove(hashtag);
                        }
                    }
                    
                    // Also clean up group hashtag indices
                    for (Map.Entry<String, Set<UUID>> entry : groupHashtagMessages.entrySet()) {
                        if (entry.getKey().endsWith(":" + hashtag)) {
                            entry.getValue().remove(messageId);
                            if (entry.getValue().isEmpty()) {
                                groupHashtagMessages.remove(entry.getKey());
                            }
                        }
                    }
                }
            }
            
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            logger.error("Error removing message", e);
            return CompletableFuture.failedFuture(e);
        }
    }
}
