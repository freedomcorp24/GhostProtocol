package org.ghostprotocol.service.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class UserSubscription {
    private final UUID userId;
    private final UUID tierId;
    private final boolean trialUsed;
    private final Instant trialStart;
    private final Instant subscriptionStart;
    private final Instant subscriptionEnd;
    private final String paymentType;
    private final Instant createdAt;
    private final Instant updatedAt;

    public UserSubscription(
            @JsonProperty("userId") UUID userId,
            @JsonProperty("tierId") UUID tierId,
            @JsonProperty("trialUsed") boolean trialUsed,
            @JsonProperty("trialStart") Instant trialStart,
            @JsonProperty("subscriptionStart") Instant subscriptionStart,
            @JsonProperty("subscriptionEnd") Instant subscriptionEnd,
            @JsonProperty("paymentType") String paymentType,
            @JsonProperty("createdAt") Instant createdAt,
            @JsonProperty("updatedAt") Instant updatedAt) {
        this.userId = userId;
        this.tierId = tierId;
        this.trialUsed = trialUsed;
        this.trialStart = trialStart;
        this.subscriptionStart = subscriptionStart;
        this.subscriptionEnd = subscriptionEnd;
        this.paymentType = paymentType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonProperty
    public UUID getUserId() {
        return userId;
    }

    @JsonProperty
    public UUID getTierId() {
        return tierId;
    }

    @JsonProperty
    public boolean isTrialUsed() {
        return trialUsed;
    }

    @JsonProperty
    public Instant getTrialStart() {
        return trialStart;
    }

    @JsonProperty
    public Instant getSubscriptionStart() {
        return subscriptionStart;
    }

    @JsonProperty
    public Instant getSubscriptionEnd() {
        return subscriptionEnd;
    }

    @JsonProperty
    public String getPaymentType() {
        return paymentType;
    }

    @JsonProperty
    public Instant getCreatedAt() {
        return createdAt;
    }

    @JsonProperty
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
