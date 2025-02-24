package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class ShareLink {
  @JsonProperty
  private UUID id;

  @JsonProperty
  private UUID vaultItemId;

  @JsonProperty
  private String token;

  @JsonProperty
  private byte[] password;

  @JsonProperty
  private int maxViews;

  @JsonProperty
  private int viewCount;

  @JsonProperty
  private Instant expiresAt;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public UUID getVaultItemId() { return vaultItemId; }
  public void setVaultItemId(UUID itemId) { this.vaultItemId = itemId; }

  public String getToken() { return token; }
  public void setToken(String token) { this.token = token; }

  public byte[] getPassword() { return password; }
  public void setPassword(byte[] password) { this.password = password; }

  public int getMaxViews() { return maxViews; }
  public void setMaxViews(int maxViews) { this.maxViews = maxViews; }

  public int getViewCount() { return viewCount; }
  public void setViewCount(int viewCount) { this.viewCount = viewCount; }

  public Instant getExpiresAt() { return expiresAt; }
  public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
}
