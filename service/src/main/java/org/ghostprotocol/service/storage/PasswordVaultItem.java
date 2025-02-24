package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class PasswordVaultItem extends VaultItem {
    private final String website;
    private final String username;

    public PasswordVaultItem(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("encryptedData") byte[] encryptedData,
            @JsonProperty("website") String website,
            @JsonProperty("username") String username,
            @JsonProperty("size") long size,
            @JsonProperty("createdAt") Instant createdAt) {
        super(id, userId, encryptedData, VaultItemType.PASSWORD.name(), size, createdAt);
        this.website = website;
        this.username = username;
    }

    @JsonProperty
    public String getWebsite() {
        return website;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }
}
