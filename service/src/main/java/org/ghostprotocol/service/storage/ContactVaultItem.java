package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class ContactVaultItem extends VaultItem {
    private final String name;
    private final String phoneNumber;
    private final String email;

    public ContactVaultItem(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("encryptedData") byte[] encryptedData,
            @JsonProperty("name") String name,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("email") String email,
            @JsonProperty("size") long size,
            @JsonProperty("createdAt") Instant createdAt) {
        super(id, userId, encryptedData, VaultItemType.CONTACT.name(), size, createdAt);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }
}
