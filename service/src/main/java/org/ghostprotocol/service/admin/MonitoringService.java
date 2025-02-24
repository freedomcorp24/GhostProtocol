package org.ghostprotocol.service.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MonitoringService {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);
    private final Map<String, AtomicLong> messageCounters = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> storageUsage = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> activeUsers = new ConcurrentHashMap<>();

    public void trackMessage(String userId) {
        messageCounters.computeIfAbsent(userId, k -> new AtomicLong(0))
                      .incrementAndGet();
    }

    public void trackStorageUsage(String userId, long bytes) {
        storageUsage.computeIfAbsent(userId, k -> new AtomicLong(0))
                    .addAndGet(bytes);
    }

    public void trackUserActivity(String userId) {
        long currentTime = System.currentTimeMillis();
        activeUsers.put(userId, new AtomicLong(currentTime));
    }

    public Map<String, Long> getMessageStats() {
        Map<String, Long> stats = new ConcurrentHashMap<>();
        messageCounters.forEach((userId, counter) -> 
            stats.put(userId, counter.get())
        );
        return stats;
    }

    public Map<String, Long> getStorageStats() {
        Map<String, Long> stats = new ConcurrentHashMap<>();
        storageUsage.forEach((userId, usage) -> 
            stats.put(userId, usage.get())
        );
        return stats;
    }

    public Map<String, Long> getActiveUserStats() {
        Map<String, Long> stats = new ConcurrentHashMap<>();
        long currentTime = System.currentTimeMillis();
        activeUsers.forEach((userId, lastActive) -> {
            long timeDiff = currentTime - lastActive.get();
            if (timeDiff < 3600000) { // Active in last hour
                stats.put(userId, lastActive.get());
            }
        });
        return stats;
    }
}
