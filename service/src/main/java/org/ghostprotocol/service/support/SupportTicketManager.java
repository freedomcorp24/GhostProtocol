package org.ghostprotocol.service.support;

import org.ghostprotocol.service.admin.AdminPermission;
import org.ghostprotocol.service.admin.AdminRoleManager;
import org.ghostprotocol.service.storage.DynamoDbTable;
import org.ghostprotocol.service.util.UUIDUtil;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SupportTicketManager {
    private final DynamoDbTable<SupportTicket> ticketTable;
    private final DynamoDbTable<TicketComment> commentTable;
    private final AdminRoleManager adminRoleManager;
    private final Clock clock;

    public SupportTicketManager(
            DynamoDbTable<SupportTicket> ticketTable,
            DynamoDbTable<TicketComment> commentTable,
            AdminRoleManager adminRoleManager,
            Clock clock) {
        this.ticketTable = ticketTable;
        this.commentTable = commentTable;
        this.adminRoleManager = adminRoleManager;
        this.clock = clock;
    }

    public CompletableFuture<SupportTicket> createTicket(UUID userId, String subject, String description) {
        UUID ticketId = UUID.randomUUID();
        Instant now = clock.instant();
        
        SupportTicket ticket = new SupportTicket(
            ticketId,
            userId,
            subject,
            description,
            TicketStatus.OPEN,
            null,
            now,
            now
        );

        return CompletableFuture.runAsync(() -> ticketTable.put(ticket))
                               .thenApply(v -> ticket);
    }

    public CompletableFuture<Void> assignTicket(UUID adminId, UUID ticketId, UUID assigneeId) {
        return adminRoleManager.hasPermission(adminId, AdminPermission.HANDLE_SUPPORT_TICKETS)
            .thenCompose(hasPermission -> {
                if (!hasPermission) {
                    return CompletableFuture.failedFuture(
                        new IllegalStateException("No permission to assign tickets"));
                }

                return getTicket(ticketId).thenCompose(maybeTicket -> {
                    if (!maybeTicket.isPresent()) {
                        return CompletableFuture.failedFuture(
                            new IllegalStateException("Ticket not found"));
                    }

                    SupportTicket ticket = maybeTicket.get();
                    SupportTicket updatedTicket = new SupportTicket(
                        ticket.getId(),
                        ticket.getUserId(),
                        ticket.getSubject(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        assigneeId,
                        ticket.getCreatedAt(),
                        clock.instant()
                    );

                    return CompletableFuture.runAsync(() -> ticketTable.put(updatedTicket));
                });
            });
    }

    public CompletableFuture<Void> updateStatus(UUID adminId, UUID ticketId, TicketStatus newStatus) {
        return adminRoleManager.hasPermission(adminId, AdminPermission.HANDLE_SUPPORT_TICKETS)
            .thenCompose(hasPermission -> {
                if (!hasPermission) {
                    return CompletableFuture.failedFuture(
                        new IllegalStateException("No permission to update ticket status"));
                }

                return getTicket(ticketId).thenCompose(maybeTicket -> {
                    if (!maybeTicket.isPresent()) {
                        return CompletableFuture.failedFuture(
                            new IllegalStateException("Ticket not found"));
                    }

                    SupportTicket ticket = maybeTicket.get();
                    SupportTicket updatedTicket = new SupportTicket(
                        ticket.getId(),
                        ticket.getUserId(),
                        ticket.getSubject(),
                        ticket.getDescription(),
                        newStatus,
                        ticket.getAssignedTo(),
                        ticket.getCreatedAt(),
                        clock.instant()
                    );

                    return CompletableFuture.runAsync(() -> ticketTable.put(updatedTicket));
                });
            });
    }

    public CompletableFuture<TicketComment> addComment(
            UUID userId, UUID ticketId, String content, boolean internal) {
        return getTicket(ticketId).thenCompose(maybeTicket -> {
            if (!maybeTicket.isPresent()) {
                return CompletableFuture.failedFuture(
                    new IllegalStateException("Ticket not found"));
            }

            if (internal) {
                return adminRoleManager.hasPermission(userId, AdminPermission.HANDLE_SUPPORT_TICKETS)
                    .thenCompose(hasPermission -> {
                        if (!hasPermission) {
                            return CompletableFuture.failedFuture(
                                new IllegalStateException("No permission to add internal comments"));
                        }
                        return createComment(userId, ticketId, content, internal);
                    });
            }

            SupportTicket ticket = maybeTicket.get();
            if (!ticket.getUserId().equals(userId) && 
                !adminRoleManager.hasPermission(userId, AdminPermission.HANDLE_SUPPORT_TICKETS)
                    .join()) {
                return CompletableFuture.failedFuture(
                    new IllegalStateException("No permission to comment on this ticket"));
            }

            return createComment(userId, ticketId, content, internal);
        });
    }

    private CompletableFuture<TicketComment> createComment(
            UUID userId, UUID ticketId, String content, boolean internal) {
        TicketComment comment = new TicketComment(
            UUID.randomUUID(),
            ticketId,
            userId,
            content,
            internal,
            clock.instant()
        );

        return CompletableFuture.runAsync(() -> commentTable.put(comment))
                               .thenApply(v -> comment);
    }

    public CompletableFuture<Optional<SupportTicket>> getTicket(UUID ticketId) {
        return CompletableFuture.supplyAsync(() ->
            Optional.ofNullable(ticketTable.get(ticketId.toString()))
        );
    }

    public CompletableFuture<List<SupportTicket>> getTicketsForUser(UUID userId) {
        return CompletableFuture.supplyAsync(() ->
            ticketTable.query(userId.toString())
        );
    }

    public CompletableFuture<List<TicketComment>> getCommentsForTicket(UUID ticketId, UUID userId) {
        return adminRoleManager.hasPermission(userId, AdminPermission.HANDLE_SUPPORT_TICKETS)
            .thenCompose(isAdmin -> 
                CompletableFuture.supplyAsync(() -> {
                    List<TicketComment> comments = commentTable.query(ticketId.toString());
                    if (!isAdmin) {
                        // Filter out internal comments for non-admin users
                        comments.removeIf(TicketComment::isInternal);
                    }
                    return comments;
                })
            );
    }
}
