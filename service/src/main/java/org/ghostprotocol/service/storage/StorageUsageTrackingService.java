package org.ghostprotocol.service.storage;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import org.ghostprotocol.service.monitoring.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Service for tracking and reporting on storage usage across different types of vault items.
 */
@Singleton
public class StorageUsageTrackingService {
    private static final Logger logger = LoggerFactory.getLogger(StorageUsageTrackingService.class);
    
    private final VaultStorageManager storageManager;
    private final AmazonDynamoDB dynamoDb;
    private final String usageTableName;
    private final MonitoringService monitoringService;
    private final Clock clock;
    
    @Inject
    public StorageUsageTrackingService(
            VaultStorageManager storageManager,
            AmazonDynamoDB dynamoDb,
            String usageTableName,
            MonitoringService monitoringService,
            Clock clock) {
        this.storageManager = storageManager;
        this.dynamoDb = dynamoDb;
        this.usageTableName = usageTableName;
        this.monitoringService = monitoringService;
        this.clock = clock;
    }
    
    /**
     * Records a storage usage event when a vault item is stored or deleted.
     * 
     * @param userId The ID of the user
     * @param itemType The type of vault item
     * @param itemSize The size of the vault item in bytes
     * @param isAddition Whether the item is being added (true) or removed (false)
     * @return A CompletableFuture that completes when the event is recorded
     */
    public CompletableFuture<Void> recordStorageUsageEvent(
            UUID userId, VaultItemType itemType, long itemSize, boolean isAddition) {
        
        return CompletableFuture.runAsync(() -> {
            try {
                Instant timestamp = Instant.now(clock);
                String timestampStr = timestamp.toString();
                String userIdStr = userId.toString();
                String eventId = UUID.randomUUID().toString();
                
                Map<String, AttributeValue> item = new HashMap<>();
                item.put("event_id", new AttributeValue(eventId));
                item.put("user_id", new AttributeValue(userIdStr));
                item.put("timestamp", new AttributeValue(timestampStr));
                item.put("item_type", new AttributeValue(itemType.name()));
                item.put("item_size", new AttributeValue().withN(String.valueOf(itemSize)));
                item.put("is_addition", new AttributeValue().withBOOL(isAddition));
                
                PutItemRequest putItemRequest = new PutItemRequest()
                        .withTableName(usageTableName)
                        .withItem(item);
                
                dynamoDb.putItem(putItemRequest);
                
                // Update monitoring metrics
                String metricType = isAddition ? "added" : "removed";
                monitoringService.updateStorageUsage(itemType.name().toLowerCase(), 
                        isAddition ? itemSize : -itemSize);
                
                logger.debug("Recorded storage usage event: user={}, type={}, size={}, action={}",
                        userIdStr, itemType, itemSize, metricType);
                
            } catch (Exception e) {
                logger.error("Failed to record storage usage event", e);
                throw e;
            }
        });
    }
    
    /**
     * Gets the current storage usage for a user.
     * 
     * @param userId The ID of the user
     * @return A CompletableFuture that completes with the current storage usage in bytes
     */
    public CompletableFuture<Long> getCurrentStorageUsage(UUID userId) {
        return storageManager.getCurrentStorageUsage(userId);
    }
    
    /**
     * Gets the current storage usage for a user, broken down by item type.
     * 
     * @param userId The ID of the user
     * @return A CompletableFuture that completes with a map of item types to storage usage in bytes
     */
    public CompletableFuture<Map<VaultItemType, Long>> getStorageUsageByType(UUID userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<VaultItemType, Long> usageByType = new EnumMap<>(VaultItemType.class);
                
                // Initialize all types to zero
                for (VaultItemType type : VaultItemType.values()) {
                    usageByType.put(type, 0L);
                }
                
                // Get all items for the user
                CompletableFuture<List<VaultItem>> itemsFuture = storageManager.list(userId);
                List<VaultItem> items = itemsFuture.join();
                
                // Group by type and sum sizes
                Map<VaultItemType, Long> calculatedUsage = items.stream()
                        .collect(Collectors.groupingBy(
                                VaultItem::getType,
                                Collectors.summingLong(VaultItem::getSize)
                        ));
                
                // Update the usage map with calculated values
                usageByType.putAll(calculatedUsage);
                
                return usageByType;
                
            } catch (Exception e) {
                logger.error("Failed to get storage usage by type", e);
                throw e;
            }
        });
    }
    
    /**
     * Gets the storage usage history for a user within a time range.
     * 
     * @param userId The ID of the user
     * @param start The start time
     * @param end The end time
     * @return A CompletableFuture that completes with a list of storage usage events
     */
    public CompletableFuture<List<StorageUsageEvent>> getStorageUsageHistory(
            UUID userId, Instant start, Instant end) {
        
        return CompletableFuture.supplyAsync(() -> {
            try {
                String userIdStr = userId.toString();
                String startStr = start.toString();
                String endStr = end.toString();
                
                Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
                expressionAttributeValues.put(":userId", new AttributeValue(userIdStr));
                expressionAttributeValues.put(":start", new AttributeValue(startStr));
                expressionAttributeValues.put(":end", new AttributeValue(endStr));
                
                QueryRequest queryRequest = new QueryRequest()
                        .withTableName(usageTableName)
                        .withKeyConditionExpression("user_id = :userId AND timestamp BETWEEN :start AND :end")
                        .withExpressionAttributeValues(expressionAttributeValues);
                
                QueryResult result = dynamoDb.query(queryRequest);
                
                List<StorageUsageEvent> events = new ArrayList<>();
                
                for (Map<String, AttributeValue> item : result.getItems()) {
                    String eventId = item.get("event_id").getS();
                    Instant timestamp = Instant.parse(item.get("timestamp").getS());
                    VaultItemType itemType = VaultItemType.valueOf(item.get("item_type").getS());
                    long itemSize = Long.parseLong(item.get("item_size").getN());
                    boolean isAddition = item.get("is_addition").getBOOL();
                    
                    StorageUsageEvent event = new StorageUsageEvent(
                            UUID.fromString(eventId),
                            userId,
                            timestamp,
                            itemType,
                            itemSize,
                            isAddition
                    );
                    
                    events.add(event);
                }
                
                // Sort by timestamp
                events.sort(Comparator.comparing(StorageUsageEvent::getTimestamp));
                
                return events;
                
            } catch (Exception e) {
                logger.error("Failed to get storage usage history", e);
                throw e;
            }
        });
    }
    
    /**
     * Gets the storage usage trend for a user within a time range, aggregated by the specified period.
     * 
     * @param userId The ID of the user
     * @param start The start time
     * @param end The end time
     * @param periodHours The aggregation period in hours
     * @return A CompletableFuture that completes with a map of timestamps to storage usage in bytes
     */
    public CompletableFuture<Map<Instant, Long>> getStorageUsageTrend(
            UUID userId, Instant start, Instant end, int periodHours) {
        
        return getStorageUsageHistory(userId, start, end)
                .thenApply(events -> {
                    // Calculate the running total at each event
                    Map<Instant, Long> runningTotal = new TreeMap<>();
                    long total = 0;
                    
                    for (StorageUsageEvent event : events) {
                        if (event.isAddition()) {
                            total += event.getItemSize();
                        } else {
                            total -= event.getItemSize();
                        }
                        runningTotal.put(event.getTimestamp(), total);
                    }
                    
                    // Aggregate by period
                    Map<Instant, List<Long>> aggregatedData = new HashMap<>();
                    
                    for (Map.Entry<Instant, Long> entry : runningTotal.entrySet()) {
                        Instant timestamp = entry.getKey();
                        Instant bucketStart = timestamp.truncatedTo(ChronoUnit.HOURS)
                                .plus((timestamp.getEpochSecond() / (periodHours * 3600)) * periodHours, ChronoUnit.HOURS);
                        
                        aggregatedData.computeIfAbsent(bucketStart, k -> new ArrayList<>()).add(entry.getValue());
                    }
                    
                    // Take the last value in each bucket
                    Map<Instant, Long> result = new TreeMap<>();
                    for (Map.Entry<Instant, List<Long>> entry : aggregatedData.entrySet()) {
                        List<Long> values = entry.getValue();
                        result.put(entry.getKey(), values.get(values.size() - 1));
                    }
                    
                    return result;
                });
    }
    
    /**
     * Gets the storage quota for a user.
     * 
     * @param userId The ID of the user
     * @param isPremium Whether the user has a premium subscription
     * @param subscriptionStart The start time of the subscription
     * @return A CompletableFuture that completes with the storage quota in bytes
     */
    public CompletableFuture<Long> getStorageQuota(UUID userId, boolean isPremium, Instant subscriptionStart) {
        return CompletableFuture.supplyAsync(() -> {
            if (isPremium) {
                return VaultStorageManager.PREMIUM_STORAGE_LIMIT;
            } else {
                // Check if trial is still valid
                if (clock.instant().isAfter(subscriptionStart.plus(VaultStorageManager.TRIAL_DURATION))) {
                    return 0L;
                }
                return VaultStorageManager.TRIAL_STORAGE_LIMIT;
            }
        });
    }
    
    /**
     * Gets a storage usage report for a user.
     * 
     * @param userId The ID of the user
     * @param isPremium Whether the user has a premium subscription
     * @param subscriptionStart The start time of the subscription
     * @return A CompletableFuture that completes with a storage usage report
     */
    public CompletableFuture<StorageUsageReport> getStorageUsageReport(
            UUID userId, boolean isPremium, Instant subscriptionStart) {
        
        CompletableFuture<Long> currentUsageFuture = getCurrentStorageUsage(userId);
        CompletableFuture<Map<VaultItemType, Long>> usageByTypeFuture = getStorageUsageByType(userId);
        CompletableFuture<Long> quotaFuture = getStorageQuota(userId, isPremium, subscriptionStart);
        
        // Get usage trend for the last 30 days
        Instant now = Instant.now(clock);
        Instant thirtyDaysAgo = now.minus(30, ChronoUnit.DAYS);
        CompletableFuture<Map<Instant, Long>> trendFuture = 
                getStorageUsageTrend(userId, thirtyDaysAgo, now, 24); // Daily aggregation
        
        return CompletableFuture.allOf(currentUsageFuture, usageByTypeFuture, quotaFuture, trendFuture)
                .thenApply(v -> {
                    long currentUsage = currentUsageFuture.join();
                    Map<VaultItemType, Long> usageByType = usageByTypeFuture.join();
                    long quota = quotaFuture.join();
                    Map<Instant, Long> trend = trendFuture.join();
                    
                    return new StorageUsageReport(
                            userId,
                            currentUsage,
                            quota,
                            usageByType,
                            trend,
                            now
                    );
                });
    }
    
    /**
     * Data class for storage usage events.
     */
    public static class StorageUsageEvent {
        private final UUID eventId;
        private final UUID userId;
        private final Instant timestamp;
        private final VaultItemType itemType;
        private final long itemSize;
        private final boolean isAddition;
        
        public StorageUsageEvent(
                UUID eventId,
                UUID userId,
                Instant timestamp,
                VaultItemType itemType,
                long itemSize,
                boolean isAddition) {
            this.eventId = eventId;
            this.userId = userId;
            this.timestamp = timestamp;
            this.itemType = itemType;
            this.itemSize = itemSize;
            this.isAddition = isAddition;
        }
        
        public UUID getEventId() {
            return eventId;
        }
        
        public UUID getUserId() {
            return userId;
        }
        
        public Instant getTimestamp() {
            return timestamp;
        }
        
        public VaultItemType getItemType() {
            return itemType;
        }
        
        public long getItemSize() {
            return itemSize;
        }
        
        public boolean isAddition() {
            return isAddition;
        }
    }
    
    /**
     * Data class for storage usage reports.
     */
    public static class StorageUsageReport {
        private final UUID userId;
        private final long currentUsage;
        private final long quota;
        private final Map<VaultItemType, Long> usageByType;
        private final Map<Instant, Long> usageTrend;
        private final Instant generatedAt;
        
        public StorageUsageReport(
                UUID userId,
                long currentUsage,
                long quota,
                Map<VaultItemType, Long> usageByType,
                Map<Instant, Long> usageTrend,
                Instant generatedAt) {
            this.userId = userId;
            this.currentUsage = currentUsage;
            this.quota = quota;
            this.usageByType = usageByType;
            this.usageTrend = usageTrend;
            this.generatedAt = generatedAt;
        }
        
        public UUID getUserId() {
            return userId;
        }
        
        public long getCurrentUsage() {
            return currentUsage;
        }
        
        public long getQuota() {
            return quota;
        }
        
        public double getUsagePercentage() {
            return quota > 0 ? ((double) currentUsage / quota) * 100.0 : 0.0;
        }
        
        public Map<VaultItemType, Long> getUsageByType() {
            return usageByType;
        }
        
        public Map<Instant, Long> getUsageTrend() {
            return usageTrend;
        }
        
        public Instant getGeneratedAt() {
            return generatedAt;
        }
    }
}
