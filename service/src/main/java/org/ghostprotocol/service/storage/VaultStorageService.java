package org.ghostprotocol.service.storage;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.ghostprotocol.service.storage.SubscriptionManager;

public class VaultStorageService {
    private final VaultStorageManager storageManager;
    private final SubscriptionManager subscriptionManager;
    private final PasswordGenerator passwordGenerator;
    private final Clock clock;

    public VaultStorageService(
            VaultStorageManager storageManager,
            SubscriptionManager subscriptionManager,
            Clock clock) {
        this.storageManager = storageManager;
        this.subscriptionManager = subscriptionManager;
        this.passwordGenerator = new PasswordGenerator();
        this.clock = clock;
    }

    public CompletableFuture<Void> storeItem(UUID userId, VaultItem item) {
        return subscriptionManager.getSubscriptionInformation(new SubscriptionManager.SubscriberCredentials(userId))
            .thenCompose(subInfo -> {
                boolean isPremium = subInfo.isPresent();
                Instant subscriptionStart = subInfo.map(info -> Instant.ofEpochSecond(info.getStartTimestamp()))
                    .orElse(clock.instant());

                return storageManager.checkStorageLimit(userId, item.getSize(), isPremium, subscriptionStart)
                    .thenCompose(hasSpace -> {
                        if (!hasSpace) {
                            return CompletableFuture.failedFuture(
                                new IllegalStateException("Storage limit exceeded"));
                        }
                        return storageManager.store(userId, item);
                    });
            });
    }

    public CompletableFuture<Optional<VaultItem>> getItem(UUID userId, UUID itemId) {
        return storageManager.get(userId, itemId);
    }

    public CompletableFuture<List<VaultItem>> listItems(UUID userId) {
        return storageManager.list(userId);
    }

    public CompletableFuture<Void> deleteItem(UUID userId, UUID itemId) {
        return storageManager.delete(userId, itemId);
    }

    public String generatePassword(int length, boolean includeUpper, boolean includeLower,
                                 boolean includeDigits, boolean includeSpecial) {
        return passwordGenerator.generatePassword(length, includeUpper, includeLower,
                                               includeDigits, includeSpecial);
    }
}
