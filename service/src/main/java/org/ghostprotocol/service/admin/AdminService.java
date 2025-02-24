package org.ghostprotocol.service.admin;

import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.AccountsManager;
import org.ghostprotocol.service.subscription.PremiumTierManager;
import org.ghostprotocol.service.support.SupportTicketManager;
import org.ghostprotocol.service.monitoring.MonitoringService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AdminService {
    private final AccountsManager accountsManager;
    private final AdminRoleManager roleManager;
    private final PremiumTierManager tierManager;
    private final SupportTicketManager ticketManager;
    private final MonitoringService monitoringService;

    public AdminService(
            AccountsManager accountsManager,
            AdminRoleManager roleManager,
            PremiumTierManager tierManager,
            SupportTicketManager ticketManager,
            MonitoringService monitoringService) {
        this.accountsManager = accountsManager;
        this.roleManager = roleManager;
        this.tierManager = tierManager;
        this.ticketManager = ticketManager;
        this.monitoringService = monitoringService;
    }

    public void assignRole(Account admin, UUID targetUserId, AdminRole role) {
        // Verify admin has permission to assign roles
        if (!roleManager.hasPermission(admin, AdminPermission.MANAGE_ROLES)) {
            throw new AdminAuthorizationException("Insufficient permissions");
        }

        // Get target account
        Optional<Account> targetAccount = accountsManager.get(targetUserId);
        if (!targetAccount.isPresent()) {
            throw new IllegalArgumentException("Target user not found");
        }

        // Assign role
        roleManager.assignRole(targetAccount.get(), role);
    }

    public void removeRole(Account admin, UUID targetUserId) {
        if (!roleManager.hasPermission(admin, AdminPermission.MANAGE_ROLES)) {
            throw new AdminAuthorizationException("Insufficient permissions");
        }

        Optional<Account> targetAccount = accountsManager.get(targetUserId);
        if (!targetAccount.isPresent()) {
            throw new IllegalArgumentException("Target user not found");
        }

        roleManager.removeRole(targetAccount.get());
    }

    public void suspendUser(Account admin, UUID targetUserId, String reason) {
        if (!roleManager.hasPermission(admin, AdminPermission.MANAGE_USERS)) {
            throw new AdminAuthorizationException("Insufficient permissions");
        }

        Optional<Account> targetAccount = accountsManager.get(targetUserId);
        if (!targetAccount.isPresent()) {
            throw new IllegalArgumentException("Target user not found");
        }

        accountsManager.suspend(targetAccount.get(), reason);
    }

    public void unsuspendUser(Account admin, UUID targetUserId) {
        if (!roleManager.hasPermission(admin, AdminPermission.MANAGE_USERS)) {
            throw new AdminAuthorizationException("Insufficient permissions");
        }

        Optional<Account> targetAccount = accountsManager.get(targetUserId);
        if (!targetAccount.isPresent()) {
            throw new IllegalArgumentException("Target user not found");
        }

        accountsManager.unsuspend(targetAccount.get());
    }

    public List<Account> listUsers(Account admin, int offset, int limit) {
        if (!roleManager.hasPermission(admin, AdminPermission.VIEW_USERS)) {
            throw new AdminAuthorizationException("Insufficient permissions");
        }

        return accountsManager.getAll(offset, limit);
    }

    public void updateUserTier(Account admin, UUID targetUserId, String tierId) {
        if (!roleManager.hasPermission(admin, AdminPermission.MANAGE_SUBSCRIPTIONS)) {
            throw new AdminAuthorizationException("Insufficient permissions");
        }

        Optional<Account> targetAccount = accountsManager.get(targetUserId);
        if (!targetAccount.isPresent()) {
            throw new IllegalArgumentException("Target user not found");
        }

        tierManager.updateUserTier(targetAccount.get(), tierId);
    }

    public MonitoringMetrics getMonitoringMetrics(Account admin) {
        if (!roleManager.hasPermission(admin, AdminPermission.VIEW_METRICS)) {
            throw new AdminAuthorizationException("Insufficient permissions");
        }

        return monitoringService.getCurrentMetrics();
    }

    public static class AdminAuthorizationException extends RuntimeException {
        public AdminAuthorizationException(String message) {
            super(message);
        }
    }

    public static class MonitoringMetrics {
        public long activeUsers;
        public long messagesSent;
        public long storageUsed;
        public double cpuUsage;
        public double memoryUsage;
        public long errorCount;
    }
}
