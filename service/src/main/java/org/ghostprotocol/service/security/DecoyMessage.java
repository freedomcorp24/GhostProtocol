package org.ghostprotocol.service.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class DecoyMessage {
    private final UUID id;
    private final UUID userId;
    private final UUID contactId;
    private final String content;
    private final boolean sentByUser;
    private final Instant createdAt;

    public DecoyMessage(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("contactId") UUID contactId,
            @JsonProperty("content") String content,
            @JsonProperty("sentByUser") boolean sentByUser,
            @JsonProperty("createdAt") Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.contactId = contactId;
        this.content = content;
        this.sentByUser = sentByUser;
        this.createdAt = createdAt;
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
    public UUID getContactId() {
        return contactId;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public boolean isSentByUser() {
        return sentByUser;
    }

    @JsonProperty
    public Instant getCreatedAt() {
        return createdAt;
    }
}
