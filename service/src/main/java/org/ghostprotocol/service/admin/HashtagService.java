package org.ghostprotocol.service.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HashtagService {
    private static final Logger logger = LoggerFactory.getLogger(HashtagService.class);
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#(\\w+)");
    
    private final Map<String, Set<String>> hashtagMessages = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> messageHashtags = new ConcurrentHashMap<>();

    public Set<String> extractHashtags(String messageId, String content) {
        Set<String> hashtags = new HashSet<>();
        Matcher matcher = HASHTAG_PATTERN.matcher(content);
        
        while (matcher.find()) {
            String hashtag = matcher.group(1).toLowerCase();
            hashtags.add(hashtag);
            
            hashtagMessages.computeIfAbsent(hashtag, k -> ConcurrentHashMap.newKeySet())
                          .add(messageId);
            messageHashtags.computeIfAbsent(messageId, k -> ConcurrentHashMap.newKeySet())
                          .add(hashtag);
        }
        
        return hashtags;
    }

    public Set<String> getMessagesByHashtag(String hashtag) {
        return hashtagMessages.getOrDefault(hashtag.toLowerCase(), Collections.emptySet());
    }

    public Set<String> getHashtagsByMessage(String messageId) {
        return messageHashtags.getOrDefault(messageId, Collections.emptySet());
    }

    public List<String> getTrendingHashtags(int limit) {
        return hashtagMessages.entrySet().stream()
            .sorted((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()))
            .limit(limit)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    public void removeMessage(String messageId) {
        Set<String> hashtags = messageHashtags.remove(messageId);
        if (hashtags != null) {
            hashtags.forEach(hashtag -> {
                Set<String> messages = hashtagMessages.get(hashtag);
                if (messages != null) {
                    messages.remove(messageId);
                    if (messages.isEmpty()) {
                        hashtagMessages.remove(hashtag);
                    }
                }
            });
        }
    }
}
