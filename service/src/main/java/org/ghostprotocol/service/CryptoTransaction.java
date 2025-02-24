package org.ghostprotocol.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CryptoTransaction {
  public enum Status {
    PENDING,
    CONFIRMED,
    FAILED
  }

  @JsonProperty
  private UUID id;

  @JsonProperty
  private UUID walletId;

  @JsonProperty
  private String txHash;

  @JsonProperty
  private BigDecimal amount;

  @JsonProperty
  private Status status;

  @JsonProperty
  private int confirmations;

  @JsonProperty
  private Instant createdAt;

  @JsonProperty
  private Instant confirmedAt;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public UUID getWalletId() { return walletId; }
  public void setWalletId(UUID walletId) { this.walletId = walletId; }

  public String getTxHash() { return txHash; }
  public void setTxHash(String txHash) { this.txHash = txHash; }

  public BigDecimal getAmount() { return amount; }
  public void setAmount(BigDecimal amount) { this.amount = amount; }

  public Status getStatus() { return status; }
  public void setStatus(Status status) { this.status = status; }

  public int getConfirmations() { return confirmations; }
  public void setConfirmations(int confirmations) { this.confirmations = confirmations; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

  public Instant getConfirmedAt() { return confirmedAt; }
  public void setConfirmedAt(Instant confirmedAt) { this.confirmedAt = confirmedAt; }
}
