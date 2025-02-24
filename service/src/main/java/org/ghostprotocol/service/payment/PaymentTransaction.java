package org.ghostprotocol.service.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentTransaction {
    private final UUID id;
    private final UUID paymentAddressId;
    private final String transactionHash;
    private final BigDecimal amount;
    private final int confirmations;
    private final Instant createdAt;

    public PaymentTransaction(
            @JsonProperty("id") UUID id,
            @JsonProperty("paymentAddressId") UUID paymentAddressId,
            @JsonProperty("transactionHash") String transactionHash,
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("confirmations") int confirmations,
            @JsonProperty("createdAt") Instant createdAt) {
        this.id = id;
        this.paymentAddressId = paymentAddressId;
        this.transactionHash = transactionHash;
        this.amount = amount;
        this.confirmations = confirmations;
        this.createdAt = createdAt;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public UUID getPaymentAddressId() {
        return paymentAddressId;
    }

    @JsonProperty
    public String getTransactionHash() {
        return transactionHash;
    }

    @JsonProperty
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty
    public int getConfirmations() {
        return confirmations;
    }

    @JsonProperty
    public Instant getCreatedAt() {
        return createdAt;
    }
}
