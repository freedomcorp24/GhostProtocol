package org.ghostprotocol.service.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentAddress {
    private final UUID id;
    private final UUID userId;
    private final String currency;
    private final String address;
    private final BigDecimal amount;
    private final String status;
    private final Instant createdAt;
    private final Instant expiresAt;

    public PaymentAddress(
            @JsonProperty("id") UUID id,
            @JsonProperty("userId") UUID userId,
            @JsonProperty("currency") String currency,
            @JsonProperty("address") String address,
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("status") String status,
            @JsonProperty("createdAt") Instant createdAt,
            @JsonProperty("expiresAt") Instant expiresAt) {
        this.id = id;
        this.userId = userId;
        this.currency = currency;
        this.address = address;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public UUID getUserId() {
        return userId;
    }

    @JsonProperty
    public String getCurrency() {
        return currency;
    }

    @JsonProperty
    public String getAddress() {
        return address;
    }

    @JsonProperty
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty
    public String getStatus() {
        return status;
    }

    @JsonProperty
    public Instant getCreatedAt() {
        return createdAt;
    }

    @JsonProperty
    public Instant getExpiresAt() {
        return expiresAt;
    }
}
