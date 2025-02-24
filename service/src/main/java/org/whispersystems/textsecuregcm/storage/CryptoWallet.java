package org.whispersystems.textsecuregcm.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class CryptoWallet {
  public enum CoinType {
    BTC,
    XMR,
    USDT
  }

  @JsonProperty
  private UUID id;

  @JsonProperty
  private UUID ownerUuid;

  @JsonProperty
  private CoinType coinType;

  @JsonProperty
  private String address;

  @JsonProperty
  private byte[] encryptedPrivateKey;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public UUID getOwnerUuid() { return ownerUuid; }
  public void setOwnerUuid(UUID ownerUuid) { this.ownerUuid = ownerUuid; }

  public CoinType getCoinType() { return coinType; }
  public void setCoinType(CoinType coinType) { this.coinType = coinType; }

  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }

  public byte[] getEncryptedPrivateKey() { return encryptedPrivateKey; }
  public void setEncryptedPrivateKey(byte[] key) { this.encryptedPrivateKey = key; }
}
