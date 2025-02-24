package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class VaultItem {
    private final UUID id;
    private final UUID userId;
    private final byte[] encryptedData;
    private final String type;
    private final long size;
    private final Instant createdAt;

    public VaultItem(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("encryptedData") byte[] encryptedData,
            @JsonProperty("type") String type,
            @JsonProperty("size") long size,
            @JsonProperty("createdAt") Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.encryptedData = encryptedData;
        this.type = type;
        this.size = size;
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
    public byte[] getEncryptedData() {
        return encryptedData;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public long getSize() {
        return size;
    }

    @JsonProperty
    public Instant getCreatedAt() {
        return createdAt;
    }
}
