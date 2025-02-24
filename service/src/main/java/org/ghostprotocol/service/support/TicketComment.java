package org.ghostprotocol.service.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class TicketComment {
    private final UUID id;
    private final UUID ticketId;
    private final UUID userId;
    private final String content;
    private final boolean internal;
    private final Instant createdAt;

    public TicketComment(
            @JsonProperty("id") UUID id,
            @JsonProperty("ticketId") UUID ticketId,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("content") String content,
            @JsonProperty("internal") boolean internal,
            @JsonProperty("createdAt") Instant createdAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.userId = userId;
        this.content = content;
        this.internal = internal;
        this.createdAt = createdAt;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public UUID getTicketId() {
        return ticketId;
    }

    @JsonProperty
    public UUID getUserId() {
        return userId;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public boolean isInternal() {
        return internal;
    }

    @JsonProperty
    public Instant getCreatedAt() {
        return createdAt;
    }
}
