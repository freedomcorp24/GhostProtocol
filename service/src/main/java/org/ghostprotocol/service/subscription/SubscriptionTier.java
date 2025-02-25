package org.ghostprotocol.service.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a subscription tier with an ID, name, price, and list of features.
 */
public class SubscriptionTier {
    private final String id;
    private final String name;
    private final double price;
    private final List<String> features;
    
    public SubscriptionTier(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("price") double price,
            @JsonProperty("features") List<String> features) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.features = features != null ? features : new ArrayList<>();
    }
    
    @JsonProperty
    public String getId() {
        return id;
    }
    
    @JsonProperty
    public String getName() {
        return name;
    }
    
    @JsonProperty
    public double getPrice() {
        return price;
    }
    
    @JsonProperty
    public List<String> getFeatures() {
        return features;
    }
}
