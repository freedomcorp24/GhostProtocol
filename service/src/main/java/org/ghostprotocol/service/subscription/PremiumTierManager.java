package org.ghostprotocol.service.subscription;

import org.ghostprotocol.service.admin.AdminPermission;
import org.ghostprotocol.service.admin.AdminRoleManager;
import org.ghostprotocol.service.storage.DynamoDbTable;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PremiumTierManager {
    private static final Duration TRIAL_DURATION = Duration.ofDays(7);
    private static final long TRIAL_STORAGE_LIMIT = 100 * 1024 * 1024; // 100MB
    private static final BigDecimal DEFAULT_MONTHLY_PRICE = new BigDecimal("9.99");
    private static final long DEFAULT_STORAGE_LIMIT = 1024 * 1024 * 1024; // 1GB

    private final DynamoDbTable<PremiumTier> tierTable;
    private final DynamoDbTable<UserSubscription> subscriptionTable;
    private final AdminRoleManager adminRoleManager;
    private final Clock clock;

    public PremiumTierManager(
            DynamoDbTable<PremiumTier> tierTable,
            DynamoDbTable<UserSubscription> subscriptionTable,
            AdminRoleManager adminRoleManager,
            Clock clock) {
        this.tierTable = tierTable;
        this.subscriptionTable = subscriptionTable;
        this.adminRoleManager = adminRoleManager;
        this.clock = clock;
    }

    public CompletableFuture<PremiumTier> createTier(
            UUID adminId,
            String name,
            BigDecimal monthlyPrice,
            BigDecimal yearlyPrice,
            long storageLimit) {
        return adminRoleManager.hasPermission(adminId, AdminPermission.MANAGE_PREMIUM_TIERS)
            .thenCompose(hasPermission -> {
                if (!hasPermission) {
                    return CompletableFuture.failedFuture(
                        new IllegalStateException("Only master admin can create premium tiers"));
                }

                Instant now = clock.instant();
                PremiumTier tier = new PremiumTier(
                    UUID.randomUUID(),
                    name,
                    monthlyPrice,
                    yearlyPrice,
                    storageLimit,
                    adminId,
                    now,
                    now
                );

                return CompletableFuture.runAsync(() -> tierTable.put(tier))
                                      .thenApply(v -> tier);
            });
    }

    public CompletableFuture<Optional<PremiumTier>> getTier(UUID tierId) {
        return CompletableFuture.supplyAsync(() ->
            Optional.ofNullable(tierTable.get(tierId.toString()))
        );
    }

    public CompletableFuture<List<PremiumTier>> listTiers() {
        return CompletableFuture.supplyAsync(() ->
            tierTable.scan()
        );
    }

    public CompletableFuture<Void> deleteTier(UUID adminId, UUID tierId) {
        return adminRoleManager.hasPermission(adminId, AdminPermission.MANAGE_PREMIUM_TIERS)
            .thenCompose(hasPermission -> {
                if (!hasPermission) {
                    return CompletableFuture.failedFuture(
                        new IllegalStateException("Only master admin can delete premium tiers"));
                }

                return CompletableFuture.runAsync(() -> tierTable.delete(tierId.toString()));
            });
    }

    public CompletableFuture<UserSubscription> startTrial(UUID userId) {
        return CompletableFuture.supplyAsync(() -> 
            subscriptionTable.get(userId.toString())
        ).thenCompose(existingSub -> {
            if (existingSub != null && existingSub.isTrialUsed()) {
                return CompletableFuture.failedFuture(
                    new IllegalStateException("Trial already used"));
            }

            Instant now = clock.instant();
            UserSubscription subscription = new UserSubscription(
                userId,
                null, // No tier for trial
                true,
                now,
                now,
                now.plus(TRIAL_DURATION),
                "TRIAL",
                now,
                now
            );

            return CompletableFuture.runAsync(() -> subscriptionTable.put(subscription))
                                  .thenApply(v -> subscription);
        });
    }

    public CompletableFuture<UserSubscription> subscribe(
            UUID userId, UUID tierId, String paymentType) {
        return getTier(tierId).thenCompose(maybeTier -> {
            if (!maybeTier.isPresent()) {
                return CompletableFuture.failedFuture(
                    new IllegalStateException("Tier not found"));
            }

            Instant now = clock.instant();
            UserSubscription subscription = new UserSubscription(
                userId,
                tierId,
                true, // Mark trial as used when subscribing
                null,
                now,
                now.plus(Duration.ofDays(30)), // Default to monthly
                paymentType,
                now,
                now
            );

            return CompletableFuture.runAsync(() -> subscriptionTable.put(subscription))
                                  .thenApply(v -> subscription);
        });
    }

    public CompletableFuture<Optional<UserSubscription>> getSubscription(UUID userId) {
        return CompletableFuture.supplyAsync(() ->
            Optional.ofNullable(subscriptionTable.get(userId.toString()))
        );
    }

    public CompletableFuture<Boolean> isSubscriptionActive(UUID userId) {
        return getSubscription(userId).thenApply(maybeSub ->
            maybeSub.map(sub -> {
                if (sub.getTierId() == null) {
                    // Trial subscription
                    return !clock.instant().isAfter(sub.getSubscriptionEnd());
                }
                return !clock.instant().isAfter(sub.getSubscriptionEnd());
            }).orElse(false)
        );
    }

    public CompletableFuture<Long> getStorageLimit(UUID userId) {
        return getSubscription(userId).thenCompose(maybeSub -> {
            if (!maybeSub.isPresent()) {
                return CompletableFuture.completedFuture(0L);
            }

            UserSubscription sub = maybeSub.get();
            if (sub.getTierId() == null) {
                // Trial subscription
                return CompletableFuture.completedFuture(TRIAL_STORAGE_LIMIT);
            }

            return getTier(sub.getTierId()).thenApply(maybeTier ->
                maybeTier.map(PremiumTier::getStorageLimit)
                         .orElse(0L)
            );
        });
    }
}
