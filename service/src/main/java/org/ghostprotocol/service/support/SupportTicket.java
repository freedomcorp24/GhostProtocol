package org.ghostprotocol.service.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class SupportTicket {
    private final UUID id;
    private final UUID userId;
    private final String subject;
    private final String description;
    private final TicketStatus status;
    private final UUID assignedTo;
    private final Instant createdAt;
    private final Instant updatedAt;

    public SupportTicket(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("subject") String subject,
            @JsonProperty("description") String description,
            @JsonProperty("status") TicketStatus status,
            @JsonProperty("assignedTo") UUID assignedTo,
            @JsonProperty("createdAt") Instant createdAt,
            @JsonProperty("updatedAt") Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public UUID getUserId() {
        return userId;
    }

    @JsonProperty
    public String getSubject() {
        return subject;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public TicketStatus getStatus() {
        return status;
    }

    @JsonProperty
    public UUID getAssignedTo() {
        return assignedTo;
    }

    @JsonProperty
    public Instant getCreatedAt() {
        return createdAt;
    }

    @JsonProperty
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
