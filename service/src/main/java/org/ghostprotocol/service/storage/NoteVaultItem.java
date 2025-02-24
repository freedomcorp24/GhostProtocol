package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class NoteVaultItem extends VaultItem {
    private final String title;

    public NoteVaultItem(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("encryptedData") byte[] encryptedData,
            @JsonProperty("title") String title,
            @JsonProperty("size") long size,
            @JsonProperty("createdAt") Instant createdAt) {
        super(id, userId, encryptedData, VaultItemType.NOTE.name(), size, createdAt);
        this.title = title;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }
}
