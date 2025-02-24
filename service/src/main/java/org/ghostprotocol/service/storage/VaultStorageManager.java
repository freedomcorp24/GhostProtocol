package org.ghostprotocol.service.storage;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.s3.AmazonS3;
import org.ghostprotocol.service.storage.DynamoDbTable;
import org.ghostprotocol.service.util.UUIDUtil;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class VaultStorageManager {
    private static final long PREMIUM_STORAGE_LIMIT = 1024 * 1024 * 1024; // 1GB
    private static final long TRIAL_STORAGE_LIMIT = 100 * 1024 * 1024; // 100MB
    private static final Duration TRIAL_DURATION = Duration.ofDays(7);

    private final DynamoDbTable<VaultItem> vaultTable;
    private final AmazonS3 s3Client;
    private final String bucketName;
    private final Clock clock;

    public VaultStorageManager(
            DynamoDbTable<VaultItem> vaultTable,
            AmazonS3 s3Client, 
            String bucketName,
            Clock clock) {
        this.vaultTable = vaultTable;
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.clock = clock;
    }

    public CompletableFuture<Void> store(UUID userId, VaultItem item) {
        return CompletableFuture.runAsync(() -> {
            String key = String.format("%s/%s", UUIDUtil.toBase64(userId), UUIDUtil.toBase64(item.getId()));
            s3Client.putObject(bucketName, key, new String(item.getEncryptedData()));
            vaultTable.put(item);
        });
    }

    public CompletableFuture<Optional<VaultItem>> get(UUID userId, UUID itemId) {
        return CompletableFuture.supplyAsync(() -> {
            String key = String.format("%s/%s", UUIDUtil.toBase64(userId), UUIDUtil.toBase64(itemId));
            return Optional.ofNullable(vaultTable.get(userId.toString(), itemId.toString()));
        });
    }

    public CompletableFuture<List<VaultItem>> list(UUID userId) {
        return CompletableFuture.supplyAsync(() -> 
            vaultTable.query(userId.toString())
        );
    }

    public CompletableFuture<Long> getCurrentStorageUsage(UUID userId) {
        return list(userId).thenApply(items ->
            items.stream().mapToLong(VaultItem::getSize).sum()
        );
    }

    public CompletableFuture<Boolean> checkStorageLimit(UUID userId, long itemSize, boolean isPremium, Instant subscriptionStart) {
        return getCurrentStorageUsage(userId).thenApply(currentUsage -> {
            long limit;
            if (isPremium) {
                limit = PREMIUM_STORAGE_LIMIT;
            } else {
                // Check if trial is still valid
                if (clock.instant().isAfter(subscriptionStart.plus(TRIAL_DURATION))) {
                    return false;
                }
                limit = TRIAL_STORAGE_LIMIT;
            }
            return (currentUsage + itemSize) <= limit;
        });
    }

    public CompletableFuture<Void> delete(UUID userId, UUID itemId) {
        return CompletableFuture.runAsync(() -> {
            String key = String.format("%s/%s", UUIDUtil.toBase64(userId), UUIDUtil.toBase64(itemId));
            s3Client.deleteObject(bucketName, key);
            vaultTable.delete(userId.toString(), itemId.toString());
        });
    }
}
