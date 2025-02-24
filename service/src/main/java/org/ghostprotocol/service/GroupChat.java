package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class GroupChat {
  @JsonProperty
  private UUID id;

  @JsonProperty
  private String name;

  @JsonProperty
  private UUID creatorUuid;

  @JsonProperty
  private byte[] encryptionKey;

  @JsonProperty
  private long messageExpirationTimer;

  @JsonProperty
  private Instant createdAt;

  @JsonProperty
  private boolean archived;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public UUID getCreatorUuid() { return creatorUuid; }
  public void setCreatorUuid(UUID creatorUuid) { this.creatorUuid = creatorUuid; }

  public byte[] getEncryptionKey() { return encryptionKey; }
  public void setEncryptionKey(byte[] encryptionKey) { this.encryptionKey = encryptionKey; }

  public long getMessageExpirationTimer() { return messageExpirationTimer; }
  public void setMessageExpirationTimer(long timer) { this.messageExpirationTimer = timer; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

  public boolean isArchived() { return archived; }
  public void setArchived(boolean archived) { this.archived = archived; }
}
