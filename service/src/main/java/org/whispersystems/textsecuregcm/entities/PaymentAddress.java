package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentAddress {
  @JsonProperty
  private String address;

  @JsonProperty 
  private long expiresAt;

  public PaymentAddress(String address, long expiresAt) {
    this.address = address;
    this.expiresAt = expiresAt;
  }

  public String getAddress() {
    return address;
  }

  public long getExpiresAt() {
    return expiresAt;
  }
}
