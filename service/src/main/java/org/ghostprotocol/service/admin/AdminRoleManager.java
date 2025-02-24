package org.ghostprotocol.service.admin;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.ghostprotocol.service.storage.DynamoDbTable;
import org.ghostprotocol.service.util.UUIDUtil;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AdminRoleManager {
    private final DynamoDbTable<AdminRoleRecord> adminRoleTable;
    private final Clock clock;

    public AdminRoleManager(DynamoDbTable<AdminRoleRecord> adminRoleTable, Clock clock) {
        this.adminRoleTable = adminRoleTable;
        this.clock = clock;
    }

    public CompletableFuture<Void> assignRole(UUID adminId, UUID targetUserId, AdminRole role) {
        return getRole(adminId).thenCompose(adminRole -> {
            if (!adminRole.isPresent() || adminRole.get() != AdminRole.MASTER_ADMIN) {
                return CompletableFuture.failedFuture(
                    new IllegalStateException("Only master admin can assign roles"));
            }

            AdminRoleRecord record = new AdminRoleRecord(
                targetUserId,
                role,
                adminId,
                clock.instant()
            );

            return CompletableFuture.runAsync(() -> adminRoleTable.put(record));
        });
    }

    public CompletableFuture<Optional<AdminRole>> getRole(UUID userId) {
        return CompletableFuture.supplyAsync(() -> 
            Optional.ofNullable(adminRoleTable.get(userId.toString()))
                   .map(AdminRoleRecord::role)
        );
    }

    public CompletableFuture<Boolean> hasPermission(UUID userId, AdminPermission permission) {
        return getRole(userId).thenApply(role -> {
            if (!role.isPresent()) {
                return false;
            }

            switch (permission) {
                case BAN_USER:
                case UNBAN_USER:
                    return role.get() == AdminRole.MASTER_ADMIN || 
                           role.get() == AdminRole.ADMIN;
                    
                case MANAGE_PREMIUM_TIERS:
                    return role.get() == AdminRole.MASTER_ADMIN;
                    
                case HANDLE_SUPPORT_TICKETS:
                    return role.get() == AdminRole.MASTER_ADMIN || 
                           role.get() == AdminRole.ADMIN ||
                           role.get() == AdminRole.SUPPORT_STAFF;
                    
                default:
                    return false;
            }
        });
    }
}
