package org.ghostprotocol.service.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PremiumTier {
    private final UUID id;
    private final String name;
    private final BigDecimal monthlyPrice;
    private final BigDecimal yearlyPrice;
    private final long storageLimit;
    private final UUID createdBy;
    private final Instant createdAt;
    private final Instant updatedAt;

    public PremiumTier(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("monthlyPrice") BigDecimal monthlyPrice,
            @JsonProperty("yearlyPrice") BigDecimal yearlyPrice,
            @JsonProperty("storageLimit") long storageLimit,
            @JsonProperty("createdBy") UUID createdBy,
            @JsonProperty("createdAt") Instant createdAt,
            @JsonProperty("updatedAt") Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.monthlyPrice = monthlyPrice;
        this.yearlyPrice = yearlyPrice;
        this.storageLimit = storageLimit;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    @JsonProperty
    public BigDecimal getYearlyPrice() {
        return yearlyPrice;
    }

    @JsonProperty
    public long getStorageLimit() {
        return storageLimit;
    }

    @JsonProperty
    public UUID getCreatedBy() {
        return createdBy;
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
