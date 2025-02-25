package org.ghostprotocol.service.analytics;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Data model for analytics reports.
 */
public class AnalyticsReport {
    private final Instant generatedAt;
    private final Instant startTime;
    private final Instant endTime;
    private final Map<String, Object> metrics;
    private final Map<String, Map<String, Double>> timeSeriesData;
    private final Map<String, Long> featureUsage;
    private final Map<Integer, Double> retention;
    private final Map<String, Map<String, Double>> conversion;
    
    public AnalyticsReport(
            @JsonProperty("generatedAt") Instant generatedAt,
            @JsonProperty("startTime") Instant startTime,
            @JsonProperty("endTime") Instant endTime,
            @JsonProperty("metrics") Map<String, Object> metrics,
            @JsonProperty("timeSeriesData") Map<String, Map<String, Double>> timeSeriesData,
            @JsonProperty("featureUsage") Map<String, Long> featureUsage,
            @JsonProperty("retention") Map<Integer, Double> retention,
            @JsonProperty("conversion") Map<String, Map<String, Double>> conversion) {
        this.generatedAt = generatedAt;
        this.startTime = startTime;
        this.endTime = endTime;
        this.metrics = metrics != null ? metrics : new HashMap<>();
        this.timeSeriesData = timeSeriesData != null ? timeSeriesData : new HashMap<>();
        this.featureUsage = featureUsage != null ? featureUsage : new HashMap<>();
        this.retention = retention != null ? retention : new HashMap<>();
        this.conversion = conversion != null ? conversion : new HashMap<>();
    }
    
    @JsonProperty
    public Instant getGeneratedAt() {
        return generatedAt;
    }
    
    @JsonProperty
    public Instant getStartTime() {
        return startTime;
    }
    
    @JsonProperty
    public Instant getEndTime() {
        return endTime;
    }
    
    @JsonProperty
    public Map<String, Object> getMetrics() {
        return metrics;
    }
    
    @JsonProperty
    public Map<String, Map<String, Double>> getTimeSeriesData() {
        return timeSeriesData;
    }
    
    @JsonProperty
    public Map<String, Long> getFeatureUsage() {
        return featureUsage;
    }
    
    @JsonProperty
    public Map<Integer, Double> getRetention() {
        return retention;
    }
    
    @JsonProperty
    public Map<String, Map<String, Double>> getConversion() {
        return conversion;
    }
    
    /**
     * Builder for creating AnalyticsReport instances.
     */
    public static class Builder {
        private Instant generatedAt = Instant.now();
        private Instant startTime;
        private Instant endTime;
        private Map<String, Object> metrics = new HashMap<>();
        private Map<String, Map<String, Double>> timeSeriesData = new HashMap<>();
        private Map<String, Long> featureUsage = new HashMap<>();
        private Map<Integer, Double> retention = new HashMap<>();
        private Map<String, Map<String, Double>> conversion = new HashMap<>();
        
        public Builder withGeneratedAt(Instant generatedAt) {
            this.generatedAt = generatedAt;
            return this;
        }
        
        public Builder withTimeRange(Instant startTime, Instant endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            return this;
        }
        
        public Builder withMetrics(Map<String, Object> metrics) {
            this.metrics = metrics;
            return this;
        }
        
        public Builder withTimeSeriesData(Map<String, Map<String, Double>> timeSeriesData) {
            this.timeSeriesData = timeSeriesData;
            return this;
        }
        
        public Builder withFeatureUsage(Map<String, Long> featureUsage) {
            this.featureUsage = featureUsage;
            return this;
        }
        
        public Builder withRetention(Map<Integer, Double> retention) {
            this.retention = retention;
            return this;
        }
        
        public Builder withConversion(Map<String, Map<String, Double>> conversion) {
            this.conversion = conversion;
            return this;
        }
        
        public AnalyticsReport build() {
            return new AnalyticsReport(
                    generatedAt,
                    startTime,
                    endTime,
                    metrics,
                    timeSeriesData,
                    featureUsage,
                    retention,
                    conversion
            );
        }
    }
}
