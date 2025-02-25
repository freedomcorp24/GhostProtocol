package org.ghostprotocol.service.admin;

import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.subscription.SubscriptionTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service for admin operations including user management, premium account management,
 * and product tier management.
 */
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    
    private final AdminRoleManager roleManager;
    private final Clock clock;
    
    // In-memory storage for banned users
    private final Map<UUID, BannedUserRecord> bannedUsers = new ConcurrentHashMap<>();
    
    // In-memory storage for subscription tiers
    private final Map<String, SubscriptionTier> subscriptionTiers = new ConcurrentHashMap<>();
    
    @Inject
    public AdminService(AdminRoleManager roleManager, Clock clock) {
        this.roleManager = roleManager;
        this.clock = clock;
        
        // Initialize with default subscription tiers
        SubscriptionTier freeTier = new SubscriptionTier(
                "free",
                "Free Tier",
                0.0,
                Arrays.asList("Basic messaging", "Group chats (up to 10 people)")
        );
        
        SubscriptionTier premiumTier = new SubscriptionTier(
                "premium",
                "Premium Tier",
                9.99,
                Arrays.asList("Unlimited messaging", "Group chats (up to 100 people)", 
                        "Screen sharing", "File sharing (up to 1GB)")
        );
        
        SubscriptionTier businessTier = new SubscriptionTier(
                "business",
                "Business Tier",
                19.99,
                Arrays.asList("Unlimited messaging", "Group chats (unlimited)", 
                        "Screen sharing", "File sharing (up to 10GB)", 
                        "Admin controls", "Priority support")
        );
        
        subscriptionTiers.put(freeTier.getId(), freeTier);
        subscriptionTiers.put(premiumTier.getId(), premiumTier);
        subscriptionTiers.put(businessTier.getId(), businessTier);
    }
    
    /**
     * Bans a user from the platform.
     *
     * @param adminAccount The admin account performing the ban
     * @param userId       The UUID of the user to ban
     * @param reason       The reason for the ban
     * @param duration     The duration of the ban in days (0 for permanent)
     * @return true if the user was banned, false otherwise
     */
    public boolean banUser(Account adminAccount, UUID userId, String reason, int duration) {
        if (!hasPermission(adminAccount, AdminPermission.BAN_USER)) {
            logger.warn("Admin {} attempted to ban user {} without permission", 
                    adminAccount.getUuid(), userId);
            return false;
        }
        
        Instant expirationTime = duration > 0 
                ? Instant.now(clock).plusSeconds(duration * 86400L) 
                : null;
        
        BannedUserRecord record = new BannedUserRecord(
                userId,
                adminAccount.getUuid(),
                reason,
                Instant.now(clock),
                expirationTime
        );
        
        bannedUsers.put(userId, record);
        logger.info("User {} banned by admin {} until {}", 
                userId, adminAccount.getUuid(), expirationTime);
        
        return true;
    }
    
    /**
     * Unbans a user from the platform.
     *
     * @param adminAccount The admin account performing the unban
     * @param userId       The UUID of the user to unban
     * @return true if the user was unbanned, false otherwise
     */
    public boolean unbanUser(Account adminAccount, UUID userId) {
        if (!hasPermission(adminAccount, AdminPermission.UNBAN_USER)) {
            logger.warn("Admin {} attempted to unban user {} without permission", 
                    adminAccount.getUuid(), userId);
            return false;
        }
        
        BannedUserRecord record = bannedUsers.remove(userId);
        if (record != null) {
            logger.info("User {} unbanned by admin {}", userId, adminAccount.getUuid());
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks if a user is currently banned.
     *
     * @param userId The UUID of the user to check
     * @return true if the user is banned, false otherwise
     */
    public boolean isUserBanned(UUID userId) {
        BannedUserRecord record = bannedUsers.get(userId);
        if (record == null) {
            return false;
        }
        
        // Check if the ban has expired
        if (record.getExpirationTime() != null && 
                record.getExpirationTime().isBefore(Instant.now(clock))) {
            bannedUsers.remove(userId);
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets the ban record for a user.
     *
     * @param userId The UUID of the user to check
     * @return the ban record, or null if the user is not banned
     */
    public BannedUserRecord getBanRecord(UUID userId) {
        BannedUserRecord record = bannedUsers.get(userId);
        if (record == null) {
            return null;
        }
        
        // Check if the ban has expired
        if (record.getExpirationTime() != null && 
                record.getExpirationTime().isBefore(Instant.now(clock))) {
            bannedUsers.remove(userId);
            return null;
        }
        
        return record;
    }
    
    /**
     * Gets all banned users.
     *
     * @param adminAccount The admin account requesting the list
     * @return a list of all banned users
     */
    public List<BannedUserRecord> getAllBannedUsers(Account adminAccount) {
        if (!hasPermission(adminAccount, AdminPermission.BAN_USER)) {
            logger.warn("Admin {} attempted to get banned users without permission", 
                    adminAccount.getUuid());
            return Collections.emptyList();
        }
        
        List<BannedUserRecord> result = new ArrayList<>();
        Instant now = Instant.now(clock);
        
        for (BannedUserRecord record : bannedUsers.values()) {
            // Skip expired bans
            if (record.getExpirationTime() != null && 
                    record.getExpirationTime().isBefore(now)) {
                continue;
            }
            
            result.add(record);
        }
        
        return result;
    }
    
    /**
     * Upgrades a user's account to a premium tier.
     *
     * @param adminAccount The admin account performing the upgrade
     * @param userAccount  The user account to upgrade
     * @param tierId       The ID of the tier to upgrade to
     * @return true if the upgrade was successful, false otherwise
     */
    public boolean upgradeUserAccount(Account adminAccount, Account userAccount, String tierId) {
        if (!hasPermission(adminAccount, AdminPermission.MANAGE_PREMIUM_TIERS)) {
            logger.warn("Admin {} attempted to upgrade user {} without permission", 
                    adminAccount.getUuid(), userAccount.getUuid());
            return false;
        }
        
        SubscriptionTier tier = subscriptionTiers.get(tierId);
        if (tier == null) {
            logger.warn("Admin {} attempted to upgrade user {} to non-existent tier {}", 
                    adminAccount.getUuid(), userAccount.getUuid(), tierId);
            return false;
        }
        
        // In a real implementation, this would update the user's subscription in the database
        // For now, we'll just log the action
        logger.info("User {} upgraded to tier {} by admin {}", 
                userAccount.getUuid(), tierId, adminAccount.getUuid());
        
        return true;
    }
    
    /**
     * Creates a new subscription tier.
     *
     * @param adminAccount The admin account creating the tier
     * @param id           The ID of the new tier
     * @param name         The name of the new tier
     * @param price        The price of the new tier
     * @param features     The features included in the new tier
     * @return the created tier, or null if creation failed
     */
    public SubscriptionTier createSubscriptionTier(Account adminAccount, String id, 
                                                  String name, double price, List<String> features) {
        if (!hasPermission(adminAccount, AdminPermission.MANAGE_PREMIUM_TIERS)) {
            logger.warn("Admin {} attempted to create subscription tier without permission", 
                    adminAccount.getUuid());
            return null;
        }
        
        if (subscriptionTiers.containsKey(id)) {
            logger.warn("Admin {} attempted to create subscription tier with existing ID {}", 
                    adminAccount.getUuid(), id);
            return null;
        }
        
        SubscriptionTier tier = new SubscriptionTier(id, name, price, features);
        subscriptionTiers.put(id, tier);
        
        logger.info("Subscription tier {} created by admin {}", id, adminAccount.getUuid());
        
        return tier;
    }
    
    /**
     * Updates an existing subscription tier.
     *
     * @param adminAccount The admin account updating the tier
     * @param id           The ID of the tier to update
     * @param name         The new name of the tier
     * @param price        The new price of the tier
     * @param features     The new features included in the tier
     * @return the updated tier, or null if update failed
     */
    public SubscriptionTier updateSubscriptionTier(Account adminAccount, String id, 
                                                  String name, double price, List<String> features) {
        if (!hasPermission(adminAccount, AdminPermission.MANAGE_PREMIUM_TIERS)) {
            logger.warn("Admin {} attempted to update subscription tier without permission", 
                    adminAccount.getUuid());
            return null;
        }
        
        if (!subscriptionTiers.containsKey(id)) {
            logger.warn("Admin {} attempted to update non-existent subscription tier {}", 
                    adminAccount.getUuid(), id);
            return null;
        }
        
        SubscriptionTier tier = new SubscriptionTier(id, name, price, features);
        subscriptionTiers.put(id, tier);
        
        logger.info("Subscription tier {} updated by admin {}", id, adminAccount.getUuid());
        
        return tier;
    }
    
    /**
     * Deletes a subscription tier.
     *
     * @param adminAccount The admin account deleting the tier
     * @param id           The ID of the tier to delete
     * @return true if the tier was deleted, false otherwise
     */
    public boolean deleteSubscriptionTier(Account adminAccount, String id) {
        if (!hasPermission(adminAccount, AdminPermission.MANAGE_PREMIUM_TIERS)) {
            logger.warn("Admin {} attempted to delete subscription tier without permission", 
                    adminAccount.getUuid());
            return false;
        }
        
        if (!subscriptionTiers.containsKey(id)) {
            logger.warn("Admin {} attempted to delete non-existent subscription tier {}", 
                    adminAccount.getUuid(), id);
            return false;
        }
        
        // Don't allow deleting the free tier
        if ("free".equals(id)) {
            logger.warn("Admin {} attempted to delete the free tier", adminAccount.getUuid());
            return false;
        }
        
        subscriptionTiers.remove(id);
        
        logger.info("Subscription tier {} deleted by admin {}", id, adminAccount.getUuid());
        
        return true;
    }
    
    /**
     * Gets all subscription tiers.
     *
     * @return a list of all subscription tiers
     */
    public List<SubscriptionTier> getAllSubscriptionTiers() {
        return new ArrayList<>(subscriptionTiers.values());
    }
    
    /**
     * Gets a subscription tier by ID.
     *
     * @param id The ID of the tier to get
     * @return the subscription tier, or null if not found
     */
    public SubscriptionTier getSubscriptionTier(String id) {
        return subscriptionTiers.get(id);
    }
    
    /**
     * Checks if an admin has a specific permission.
     *
     * @param adminAccount The admin account to check
     * @param permission   The permission to check for
     * @return true if the admin has the permission, false otherwise
     */
    private boolean hasPermission(Account adminAccount, AdminPermission permission) {
        if (!adminAccount.isAdmin()) {
            return false;
        }
        
        AdminRole role = roleManager.getAdminRole(adminAccount.getUuid());
        
        // Master admins can do everything
        if (role == AdminRole.MASTER_ADMIN) {
            return true;
        }
        
        // Regular admins can ban/unban users
        if (role == AdminRole.ADMIN && 
                (permission == AdminPermission.BAN_USER || permission == AdminPermission.UNBAN_USER)) {
            return true;
        }
        
        // Support staff can handle support tickets
        if (role == AdminRole.SUPPORT_STAFF && 
                permission == AdminPermission.HANDLE_SUPPORT_TICKETS) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Record of a banned user.
     */
    public static class BannedUserRecord {
        private final UUID userId;
        private final UUID bannedBy;
        private final String reason;
        private final Instant banTime;
        private final Instant expirationTime;
        
        public BannedUserRecord(UUID userId, UUID bannedBy, String reason, 
                               Instant banTime, Instant expirationTime) {
            this.userId = userId;
            this.bannedBy = bannedBy;
            this.reason = reason;
            this.banTime = banTime;
            this.expirationTime = expirationTime;
        }
        
        public UUID getUserId() {
            return userId;
        }
        
        public UUID getBannedBy() {
            return bannedBy;
        }
        
        public String getReason() {
            return reason;
        }
        
        public Instant getBanTime() {
            return banTime;
        }
        
        public Instant getExpirationTime() {
            return expirationTime;
        }
        
        public boolean isPermanent() {
            return expirationTime == null;
        }
    }
}
