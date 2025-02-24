package org.whispersystems.textsecuregcm.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class VaultItem {
  public enum ItemType {
    NOTE,
    PASSWORD,
    FILE,
    CONTACT
  }

  @JsonProperty
  private UUID id;

  @JsonProperty
  private UUID ownerUuid;

  @JsonProperty
  private ItemType type;

  @JsonProperty
  private byte[] encryptedData;

  @JsonProperty
  private Instant expiresAt;

  @JsonProperty
  private String label;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public UUID getOwnerUuid() { return ownerUuid; }
  public void setOwnerUuid(UUID ownerUuid) { this.ownerUuid = ownerUuid; }

  public ItemType getType() { return type; }
  public void setType(ItemType type) { this.type = type; }

  public byte[] getEncryptedData() { return encryptedData; }
  public void setEncryptedData(byte[] data) { this.encryptedData = data; }

  public Instant getExpiresAt() { return expiresAt; }
  public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }

  public String getLabel() { return label; }
  public void setLabel(String label) { this.label = label; }
}
