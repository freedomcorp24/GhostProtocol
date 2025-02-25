package org.ghostprotocol.service.analytics;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for processing analytics data.
 */
public class AnalyticsDataProcessor {
    
    /**
     * Aggregates time series data by the specified time period.
     * 
     * @param data The raw time series data
     * @param aggregationMinutes The aggregation period in minutes
     * @param aggregationType The type of aggregation to perform
     * @return The aggregated time series data
     */
    public static Map<Instant, Double> aggregateTimeSeriesData(
            Map<Instant, Double> data, 
            int aggregationMinutes,
            AggregationType aggregationType) {
        
        Map<Instant, List<Double>> aggregatedData = new HashMap<>();
        
        for (Map.Entry<Instant, Double> entry : data.entrySet()) {
            Instant timestamp = entry.getKey();
            Instant bucketStart = timestamp.truncatedTo(ChronoUnit.MINUTES)
                    .plus((timestamp.getEpochSecond() / (aggregationMinutes * 60)) * aggregationMinutes, ChronoUnit.MINUTES);
            
            aggregatedData.computeIfAbsent(bucketStart, k -> new ArrayList<>()).add(entry.getValue());
        }
        
        Map<Instant, Double> result = new TreeMap<>();
        for (Map.Entry<Instant, List<Double>> entry : aggregatedData.entrySet()) {
            double value;
            
            switch (aggregationType) {
                case SUM:
                    value = entry.getValue().stream().mapToDouble(Double::doubleValue).sum();
                    break;
                case AVERAGE:
                    value = entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                    break;
                case MAX:
                    value = entry.getValue().stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
                    break;
                case MIN:
                    value = entry.getValue().stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
                    break;
                case COUNT:
                    value = entry.getValue().size();
                    break;
                default:
                    value = entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                    break;
            }
            
            result.put(entry.getKey(), value);
        }
        
        return result;
    }
    
    /**
     * Calculates the growth rate between two values.
     * 
     * @param current The current value
     * @param previous The previous value
     * @return The growth rate as a percentage
     */
    public static double calculateGrowthRate(double current, double previous) {
        if (previous == 0) {
            return current > 0 ? 100.0 : 0.0;
        }
        
        return ((current - previous) / previous) * 100.0;
    }
    
    /**
     * Calculates the moving average of time series data.
     * 
     * @param data The time series data
     * @param windowSize The size of the moving average window
     * @return The moving average time series data
     */
    public static Map<Instant, Double> calculateMovingAverage(Map<Instant, Double> data, int windowSize) {
        if (data.size() <= 1) {
            return new TreeMap<>(data);
        }
        
        List<Map.Entry<Instant, Double>> sortedEntries = new ArrayList<>(data.entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey());
        
        Map<Instant, Double> result = new TreeMap<>();
        
        for (int i = 0; i < sortedEntries.size(); i++) {
            int start = Math.max(0, i - windowSize + 1);
            int end = i + 1;
            
            double sum = 0.0;
            for (int j = start; j < end; j++) {
                sum += sortedEntries.get(j).getValue();
            }
            
            double average = sum / (end - start);
            result.put(sortedEntries.get(i).getKey(), average);
        }
        
        return result;
    }
    
    /**
     * Detects anomalies in time series data using Z-score.
     * 
     * @param data The time series data
     * @param threshold The Z-score threshold for anomaly detection
     * @return A map of timestamps to boolean values indicating whether the data point is an anomaly
     */
    public static Map<Instant, Boolean> detectAnomalies(Map<Instant, Double> data, double threshold) {
        if (data.size() <= 1) {
            return data.keySet().stream().collect(Collectors.toMap(k -> k, k -> false));
        }
        
        // Calculate mean and standard deviation
        double mean = data.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        
        double sumSquaredDiff = data.values().stream()
                .mapToDouble(value -> Math.pow(value - mean, 2))
                .sum();
        
        double stdDev = Math.sqrt(sumSquaredDiff / data.size());
        
        // Detect anomalies
        Map<Instant, Boolean> anomalies = new TreeMap<>();
        
        for (Map.Entry<Instant, Double> entry : data.entrySet()) {
            double zScore = stdDev > 0 ? Math.abs((entry.getValue() - mean) / stdDev) : 0;
            anomalies.put(entry.getKey(), zScore > threshold);
        }
        
        return anomalies;
    }
    
    /**
     * Calculates the correlation between two time series.
     * 
     * @param series1 The first time series
     * @param series2 The second time series
     * @return The correlation coefficient
     */
    public static double calculateCorrelation(Map<Instant, Double> series1, Map<Instant, Double> series2) {
        // Find common timestamps
        Set<Instant> commonTimestamps = new HashSet<>(series1.keySet());
        commonTimestamps.retainAll(series2.keySet());
        
        if (commonTimestamps.size() <= 1) {
            return 0.0;
        }
        
        // Extract values for common timestamps
        List<Double> values1 = new ArrayList<>();
        List<Double> values2 = new ArrayList<>();
        
        for (Instant timestamp : commonTimestamps) {
            values1.add(series1.get(timestamp));
            values2.add(series2.get(timestamp));
        }
        
        // Calculate means
        double mean1 = values1.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double mean2 = values2.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        
        // Calculate correlation
        double numerator = 0.0;
        double denominator1 = 0.0;
        double denominator2 = 0.0;
        
        for (int i = 0; i < values1.size(); i++) {
            double diff1 = values1.get(i) - mean1;
            double diff2 = values2.get(i) - mean2;
            
            numerator += diff1 * diff2;
            denominator1 += diff1 * diff1;
            denominator2 += diff2 * diff2;
        }
        
        if (denominator1 == 0 || denominator2 == 0) {
            return 0.0;
        }
        
        return numerator / Math.sqrt(denominator1 * denominator2);
    }
    
    /**
     * Forecasts future values using simple linear regression.
     * 
     * @param data The historical time series data
     * @param forecastPeriods The number of periods to forecast
     * @param periodMinutes The length of each period in minutes
     * @return The forecasted time series data
     */
    public static Map<Instant, Double> forecastLinear(
            Map<Instant, Double> data, 
            int forecastPeriods,
            int periodMinutes) {
        
        if (data.size() <= 1) {
            return new TreeMap<>(data);
        }
        
        // Convert timestamps to numeric values (minutes since epoch)
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        
        for (Map.Entry<Instant, Double> entry : data.entrySet()) {
            x.add((double) entry.getKey().getEpochSecond() / 60);
            y.add(entry.getValue());
        }
        
        // Calculate linear regression parameters
        double meanX = x.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double meanY = y.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        
        double numerator = 0.0;
        double denominator = 0.0;
        
        for (int i = 0; i < x.size(); i++) {
            double diffX = x.get(i) - meanX;
            double diffY = y.get(i) - meanY;
            
            numerator += diffX * diffY;
            denominator += diffX * diffX;
        }
        
        double slope = denominator != 0 ? numerator / denominator : 0.0;
        double intercept = meanY - slope * meanX;
        
        // Generate forecast
        Map<Instant, Double> forecast = new TreeMap<>(data);
        
        Instant lastTimestamp = data.keySet().stream()
                .max(Instant::compareTo)
                .orElse(Instant.now());
        
        for (int i = 1; i <= forecastPeriods; i++) {
            Instant forecastTimestamp = lastTimestamp.plus(i * periodMinutes, ChronoUnit.MINUTES);
            double forecastX = (double) forecastTimestamp.getEpochSecond() / 60;
            double forecastY = slope * forecastX + intercept;
            
            forecast.put(forecastTimestamp, forecastY);
        }
        
        return forecast;
    }
    
    /**
     * Enum for aggregation types.
     */
    public enum AggregationType {
        SUM,
        AVERAGE,
        MAX,
        MIN,
        COUNT
    }
}
