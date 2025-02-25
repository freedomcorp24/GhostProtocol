package org.ghostprotocol.service.analytics;

import org.ghostprotocol.service.monitoring.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Service for advanced analytics capabilities including trend analysis,
 * user behavior tracking, and reporting features.
 */
@Singleton
public class AnalyticsService {
    private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);
    
    private final MonitoringService monitoringService;
    private final Clock clock;
    
    // Time-series data storage
    private final Map<String, NavigableMap<Instant, Double>> timeSeriesData = new ConcurrentHashMap<>();
    
    // User behavior tracking
    private final Map<String, List<UserAction>> userActions = new ConcurrentHashMap<>();
    
    // Feature usage tracking
    private final Map<String, Long> featureUsage = new ConcurrentHashMap<>();
    
    // Retention tracking
    private final Map<String, NavigableSet<Instant>> userSessions = new ConcurrentHashMap<>();
    
    // Conversion tracking
    private final Map<String, ConversionFunnel> conversionFunnels = new ConcurrentHashMap<>();
    
    @Inject
    public AnalyticsService(MonitoringService monitoringService, Clock clock) {
        this.monitoringService = monitoringService;
        this.clock = clock;
        
        // Initialize default conversion funnels
        conversionFunnels.put("registration", new ConversionFunnel(
                Arrays.asList("visit", "signup_start", "signup_complete", "first_message")
        ));
        
        conversionFunnels.put("premium", new ConversionFunnel(
                Arrays.asList("visit_premium_page", "view_pricing", "start_checkout", "complete_purchase")
        ));
    }
    
    /**
     * Records a data point in the time series data.
     * 
     * @param metric The name of the metric
     * @param value The value of the metric
     */
    public void recordTimeSeriesData(String metric, double value) {
        Instant now = Instant.now(clock);
        timeSeriesData.computeIfAbsent(metric, k -> new TreeMap<>()).put(now, value);
        
        // Also record in monitoring service if applicable
        if (metric.equals("messages_processed")) {
            monitoringService.recordMessage();
        } else if (metric.equals("active_users")) {
            monitoringService.recordUserActivity(value > 0);
        }
    }
    
    /**
     * Records a user action for behavior analysis.
     * 
     * @param userId The ID of the user
     * @param action The action performed
     * @param metadata Additional metadata about the action
     */
    public void recordUserAction(String userId, String action, Map<String, String> metadata) {
        Instant now = Instant.now(clock);
        UserAction userAction = new UserAction(userId, action, now, metadata);
        
        userActions.computeIfAbsent(userId, k -> new ArrayList<>()).add(userAction);
        
        // Update feature usage
        featureUsage.compute(action, (k, v) -> v == null ? 1 : v + 1);
        
        // Update user sessions for retention analysis
        userSessions.computeIfAbsent(userId, k -> new TreeSet<>()).add(now);
        
        // Update conversion funnels if applicable
        for (ConversionFunnel funnel : conversionFunnels.values()) {
            funnel.recordStep(userId, action, now);
        }
    }
    
    /**
     * Gets time series data for a specific metric within a time range.
     * 
     * @param metric The name of the metric
     * @param start The start time
     * @param end The end time
     * @return A map of timestamps to values
     */
    public CompletableFuture<Map<Instant, Double>> getTimeSeriesData(String metric, Instant start, Instant end) {
        NavigableMap<Instant, Double> metricData = timeSeriesData.getOrDefault(metric, new TreeMap<>());
        NavigableMap<Instant, Double> filteredData = metricData.subMap(start, true, end, true);
        
        return CompletableFuture.completedFuture(new TreeMap<>(filteredData));
    }
    
    /**
     * Gets aggregated time series data for a specific metric within a time range.
     * 
     * @param metric The name of the metric
     * @param start The start time
     * @param end The end time
     * @param aggregation The aggregation period in minutes
     * @return A map of timestamps to aggregated values
     */
    public CompletableFuture<Map<Instant, Double>> getAggregatedTimeSeriesData(
            String metric, Instant start, Instant end, int aggregation) {
        NavigableMap<Instant, Double> metricData = timeSeriesData.getOrDefault(metric, new TreeMap<>());
        NavigableMap<Instant, Double> filteredData = metricData.subMap(start, true, end, true);
        
        Map<Instant, List<Double>> aggregatedData = new HashMap<>();
        
        for (Map.Entry<Instant, Double> entry : filteredData.entrySet()) {
            Instant timestamp = entry.getKey();
            Instant bucketStart = timestamp.truncatedTo(ChronoUnit.MINUTES)
                    .plus((timestamp.getEpochSecond() / (aggregation * 60)) * aggregation, ChronoUnit.MINUTES);
            
            aggregatedData.computeIfAbsent(bucketStart, k -> new ArrayList<>()).add(entry.getValue());
        }
        
        Map<Instant, Double> result = new TreeMap<>();
        for (Map.Entry<Instant, List<Double>> entry : aggregatedData.entrySet()) {
            double average = entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            result.put(entry.getKey(), average);
        }
        
        return CompletableFuture.completedFuture(result);
    }
    
    /**
     * Gets user actions for a specific user within a time range.
     * 
     * @param userId The ID of the user
     * @param start The start time
     * @param end The end time
     * @return A list of user actions
     */
    public CompletableFuture<List<UserAction>> getUserActions(String userId, Instant start, Instant end) {
        List<UserAction> actions = userActions.getOrDefault(userId, new ArrayList<>());
        
        List<UserAction> filteredActions = actions.stream()
                .filter(action -> !action.getTimestamp().isBefore(start) && !action.getTimestamp().isAfter(end))
                .collect(Collectors.toList());
        
        return CompletableFuture.completedFuture(filteredActions);
    }
    
    /**
     * Gets feature usage statistics.
     * 
     * @return A map of feature names to usage counts
     */
    public CompletableFuture<Map<String, Long>> getFeatureUsage() {
        return CompletableFuture.completedFuture(new HashMap<>(featureUsage));
    }
    
    /**
     * Gets user retention statistics.
     * 
     * @param cohortStart The start time of the cohort
     * @param cohortEnd The end time of the cohort
     * @param periods The number of periods to analyze
     * @param periodDays The number of days in each period
     * @return A map of period numbers to retention rates
     */
    public CompletableFuture<Map<Integer, Double>> getRetentionStats(
            Instant cohortStart, Instant cohortEnd, int periods, int periodDays) {
        // Find users who were active in the cohort period
        Set<String> cohortUsers = new HashSet<>();
        
        for (Map.Entry<String, NavigableSet<Instant>> entry : userSessions.entrySet()) {
            String userId = entry.getKey();
            NavigableSet<Instant> sessions = entry.getValue();
            
            // Check if user was active in cohort period
            Instant firstSessionInCohort = sessions.ceiling(cohortStart);
            if (firstSessionInCohort != null && !firstSessionInCohort.isAfter(cohortEnd)) {
                cohortUsers.add(userId);
            }
        }
        
        // Calculate retention for each period
        Map<Integer, Double> retentionRates = new HashMap<>();
        
        for (int period = 1; period <= periods; period++) {
            Instant periodStart = cohortEnd.plus(period * periodDays, ChronoUnit.DAYS);
            Instant periodEnd = periodStart.plus(periodDays, ChronoUnit.DAYS);
            
            int activeUsers = 0;
            for (String userId : cohortUsers) {
                NavigableSet<Instant> sessions = userSessions.getOrDefault(userId, new TreeSet<>());
                
                // Check if user was active in this period
                Instant firstSessionInPeriod = sessions.ceiling(periodStart);
                if (firstSessionInPeriod != null && !firstSessionInPeriod.isAfter(periodEnd)) {
                    activeUsers++;
                }
            }
            
            double retentionRate = cohortUsers.isEmpty() ? 0.0 : (double) activeUsers / cohortUsers.size();
            retentionRates.put(period, retentionRate);
        }
        
        return CompletableFuture.completedFuture(retentionRates);
    }
    
    /**
     * Gets conversion funnel statistics.
     * 
     * @param funnelName The name of the funnel
     * @param start The start time
     * @param end The end time
     * @return A map of step names to conversion rates
     */
    public CompletableFuture<Map<String, Double>> getConversionStats(String funnelName, Instant start, Instant end) {
        ConversionFunnel funnel = conversionFunnels.get(funnelName);
        if (funnel == null) {
            return CompletableFuture.completedFuture(Collections.emptyMap());
        }
        
        return CompletableFuture.completedFuture(funnel.getConversionRates(start, end));
    }
    
    /**
     * Creates a new conversion funnel.
     * 
     * @param name The name of the funnel
     * @param steps The steps in the funnel
     * @return True if the funnel was created, false if it already exists
     */
    public boolean createConversionFunnel(String name, List<String> steps) {
        if (conversionFunnels.containsKey(name)) {
            return false;
        }
        
        conversionFunnels.put(name, new ConversionFunnel(steps));
        return true;
    }
    
    /**
     * Gets a comprehensive analytics report.
     * 
     * @param start The start time
     * @param end The end time
     * @return A map containing various analytics metrics
     */
    public CompletableFuture<Map<String, Object>> getAnalyticsReport(Instant start, Instant end) {
        Map<String, Object> report = new HashMap<>();
        
        // Get monitoring metrics
        CompletableFuture<Map<String, Object>> monitoringMetricsFuture = monitoringService.getDetailedMetrics();
        
        // Get time series data for key metrics
        List<CompletableFuture<Map<Instant, Double>>> timeSeriesFutures = new ArrayList<>();
        List<String> keyMetrics = Arrays.asList("messages_processed", "active_users", "failed_logins");
        
        for (String metric : keyMetrics) {
            timeSeriesFutures.add(getAggregatedTimeSeriesData(metric, start, end, 60)); // 1 hour aggregation
        }
        
        // Get feature usage
        CompletableFuture<Map<String, Long>> featureUsageFuture = getFeatureUsage();
        
        // Get retention stats
        CompletableFuture<Map<Integer, Double>> retentionStatsFuture = getRetentionStats(
                start, end, 4, 7); // 4 weeks of retention
        
        // Get conversion stats for key funnels
        List<CompletableFuture<Map<String, Double>>> conversionFutures = new ArrayList<>();
        List<String> keyFunnels = Arrays.asList("registration", "premium");
        
        for (String funnel : keyFunnels) {
            conversionFutures.add(getConversionStats(funnel, start, end));
        }
        
        // Combine all futures
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                monitoringMetricsFuture,
                featureUsageFuture,
                retentionStatsFuture
        );
        
        for (CompletableFuture<?> future : timeSeriesFutures) {
            allFutures = allFutures.thenCombine(future, (__, ___) -> null);
        }
        
        for (CompletableFuture<?> future : conversionFutures) {
            allFutures = allFutures.thenCombine(future, (__, ___) -> null);
        }
        
        // Wait for all futures to complete
        try {
            allFutures.get(5, TimeUnit.SECONDS);
            
            // Add monitoring metrics to report
            report.put("monitoring", monitoringMetricsFuture.get());
            
            // Add time series data to report
            Map<String, Map<Instant, Double>> timeSeriesData = new HashMap<>();
            for (int i = 0; i < keyMetrics.size(); i++) {
                timeSeriesData.put(keyMetrics.get(i), timeSeriesFutures.get(i).get());
            }
            report.put("time_series", timeSeriesData);
            
            // Add feature usage to report
            report.put("feature_usage", featureUsageFuture.get());
            
            // Add retention stats to report
            report.put("retention", retentionStatsFuture.get());
            
            // Add conversion stats to report
            Map<String, Map<String, Double>> conversionStats = new HashMap<>();
            for (int i = 0; i < keyFunnels.size(); i++) {
                conversionStats.put(keyFunnels.get(i), conversionFutures.get(i).get());
            }
            report.put("conversion", conversionStats);
            
        } catch (Exception e) {
            logger.error("Error generating analytics report", e);
            return CompletableFuture.completedFuture(Collections.emptyMap());
        }
        
        return CompletableFuture.completedFuture(report);
    }
    
    /**
     * Inner class representing a user action.
     */
    public static class UserAction {
        private final String userId;
        private final String action;
        private final Instant timestamp;
        private final Map<String, String> metadata;
        
        public UserAction(String userId, String action, Instant timestamp, Map<String, String> metadata) {
            this.userId = userId;
            this.action = action;
            this.timestamp = timestamp;
            this.metadata = metadata != null ? metadata : Collections.emptyMap();
        }
        
        public String getUserId() {
            return userId;
        }
        
        public String getAction() {
            return action;
        }
        
        public Instant getTimestamp() {
            return timestamp;
        }
        
        public Map<String, String> getMetadata() {
            return metadata;
        }
    }
    
    /**
     * Inner class representing a conversion funnel.
     */
    private static class ConversionFunnel {
        private final List<String> steps;
        private final Map<String, Map<Instant, String>> userProgress = new ConcurrentHashMap<>();
        
        public ConversionFunnel(List<String> steps) {
            this.steps = steps;
        }
        
        public void recordStep(String userId, String step, Instant timestamp) {
            if (!steps.contains(step)) {
                return;
            }
            
            userProgress.computeIfAbsent(userId, k -> new TreeMap<>()).put(timestamp, step);
        }
        
        public Map<String, Double> getConversionRates(Instant start, Instant end) {
            Map<String, Integer> stepCounts = new HashMap<>();
            
            // Initialize step counts
            for (String step : steps) {
                stepCounts.put(step, 0);
            }
            
            // Count users who reached each step
            for (Map.Entry<String, Map<Instant, String>> entry : userProgress.entrySet()) {
                Map<Instant, String> userSteps = entry.getValue();
                
                // Filter steps within time range
                Map<Instant, String> filteredSteps = new TreeMap<>();
                for (Map.Entry<Instant, String> stepEntry : userSteps.entrySet()) {
                    Instant timestamp = stepEntry.getKey();
                    if (!timestamp.isBefore(start) && !timestamp.isAfter(end)) {
                        filteredSteps.put(timestamp, stepEntry.getValue());
                    }
                }
                
                // Count steps reached
                Set<String> stepsReached = new HashSet<>(filteredSteps.values());
                for (String step : stepsReached) {
                    stepCounts.compute(step, (k, v) -> v + 1);
                }
            }
            
            // Calculate conversion rates
            Map<String, Double> conversionRates = new HashMap<>();
            
            int firstStepCount = stepCounts.getOrDefault(steps.get(0), 0);
            if (firstStepCount > 0) {
                for (String step : steps) {
                    int stepCount = stepCounts.getOrDefault(step, 0);
                    double conversionRate = (double) stepCount / firstStepCount;
                    conversionRates.put(step, conversionRate);
                }
            }
            
            return conversionRates;
        }
    }
}
