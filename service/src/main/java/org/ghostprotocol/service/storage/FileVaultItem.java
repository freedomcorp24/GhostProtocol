package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class FileVaultItem extends VaultItem {
    private final String filename;
    private final String mimeType;

    public FileVaultItem(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("encryptedData") byte[] encryptedData,
            @JsonProperty("filename") String filename,
            @JsonProperty("mimeType") String mimeType,
            @JsonProperty("size") long size,
            @JsonProperty("createdAt") Instant createdAt) {
        super(id, userId, encryptedData, VaultItemType.FILE.name(), size, createdAt);
        this.filename = filename;
        this.mimeType = mimeType;
    }

    @JsonProperty
    public String getFilename() {
        return filename;
    }

    @JsonProperty
    public String getMimeType() {
        return mimeType;
    }
}
