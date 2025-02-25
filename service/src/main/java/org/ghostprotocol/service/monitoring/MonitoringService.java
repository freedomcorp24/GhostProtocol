package org.ghostprotocol.service.monitoring;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Singleton
public class MonitoringService {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    // Message metrics
    private final Counter messageCounter = Counter.build()
        .name("messages_total")
        .help("Total messages processed")
        .labelNames("type")
        .register();

    private final Counter messageErrorCounter = Counter.build()
        .name("message_errors_total")
        .help("Total message processing errors")
        .labelNames("error_type")
        .register();

    private final Histogram messageProcessingTime = Histogram.build()
        .name("message_processing_seconds")
        .help("Message processing time in seconds")
        .buckets(0.01, 0.05, 0.1, 0.5, 1, 5)
        .register();

    // User metrics
    private final Gauge activeUsers = Gauge.build()
        .name("active_users")
        .help("Number of currently active users")
        .register();

    private final Counter userRegistrations = Counter.build()
        .name("user_registrations_total")
        .help("Total number of user registrations")
        .register();

    private final Counter userDeletions = Counter.build()
        .name("user_deletions_total")
        .help("Total number of user account deletions")
        .register();

    // Authentication metrics
    private final Counter failedLogins = Counter.build()
        .name("failed_logins_total")
        .help("Total number of failed login attempts")
        .labelNames("reason")
        .register();

    private final Counter successfulLogins = Counter.build()
        .name("successful_logins_total")
        .help("Total number of successful login attempts")
        .labelNames("auth_type")
        .register();

    private final Counter twoFactorAuthAttempts = Counter.build()
        .name("two_factor_auth_attempts_total")
        .help("Total number of two-factor authentication attempts")
        .labelNames("type", "success")
        .register();

    // API metrics
    private final Counter apiRequests = Counter.build()
        .name("api_requests_total")
        .help("Total API requests")
        .labelNames("endpoint", "method", "status")
        .register();

    private final Histogram apiLatency = Histogram.build()
        .name("api_latency_seconds")
        .help("API request latency in seconds")
        .labelNames("endpoint")
        .buckets(0.01, 0.05, 0.1, 0.5, 1, 5)
        .register();

    // System metrics
    private final Gauge systemMemoryUsage = Gauge.build()
        .name("system_memory_usage_bytes")
        .help("Current system memory usage in bytes")
        .register();

    private final Gauge systemCpuUsage = Gauge.build()
        .name("system_cpu_usage_percent")
        .help("Current system CPU usage percentage")
        .register();

    private final Gauge databaseConnections = Gauge.build()
        .name("database_connections")
        .help("Current number of database connections")
        .register();

    // Storage metrics
    private final Gauge storageUsage = Gauge.build()
        .name("storage_usage_bytes")
        .help("Storage usage in bytes")
        .labelNames("type")
        .register();

    private final Counter fileUploads = Counter.build()
        .name("file_uploads_total")
        .help("Total number of file uploads")
        .labelNames("file_type")
        .register();

    // Security metrics
    private final Counter securityEvents = Counter.build()
        .name("security_events_total")
        .help("Total security events")
        .labelNames("type", "severity")
        .register();

    // Rate limiters
    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    @Inject
    public MonitoringService() {}

    // Message monitoring methods
    public void recordMessage(String type) {
        messageCounter.labels(type).inc();
    }

    public void recordMessage() {
        recordMessage("default");
    }

    public void recordMessageError(String errorType) {
        messageErrorCounter.labels(errorType).inc();
    }

    public Histogram.Timer startMessageProcessingTimer() {
        return messageProcessingTime.startTimer();
    }

    // User monitoring methods
    public void recordUserActivity(boolean active) {
        if (active) {
            activeUsers.inc();
        } else {
            activeUsers.dec();
        }
    }

    public void recordUserRegistration() {
        userRegistrations.inc();
    }

    public void recordUserDeletion() {
        userDeletions.inc();
    }

    // Authentication monitoring methods
    public void recordFailedLogin(String reason) {
        failedLogins.labels(reason).inc();
    }

    public void recordFailedLogin() {
        recordFailedLogin("unknown");
    }

    public void recordSuccessfulLogin(String authType) {
        successfulLogins.labels(authType).inc();
    }

    public void recordTwoFactorAuthAttempt(String type, boolean success) {
        twoFactorAuthAttempts.labels(type, String.valueOf(success)).inc();
    }

    // API monitoring methods
    public void recordApiRequest(String endpoint, String method, int status) {
        apiRequests.labels(endpoint, method, String.valueOf(status)).inc();
    }

    public Histogram.Timer startApiLatencyTimer(String endpoint) {
        return apiLatency.labels(endpoint).startTimer();
    }

    // System monitoring methods
    public void updateSystemMemoryUsage(long bytes) {
        systemMemoryUsage.set(bytes);
    }

    public void updateSystemCpuUsage(double percent) {
        systemCpuUsage.set(percent);
    }

    public void updateDatabaseConnections(int count) {
        databaseConnections.set(count);
    }

    // Storage monitoring methods
    public void updateStorageUsage(String type, long bytes) {
        storageUsage.labels(type).set(bytes);
    }

    public void recordFileUpload(String fileType) {
        fileUploads.labels(fileType).inc();
    }

    // Security monitoring methods
    public void recordSecurityEvent(String type, String severity) {
        securityEvents.labels(type, severity).inc();
    }

    public void alertSuspiciousActivity(String userId, String activityType) {
        logger.warn("Suspicious activity detected - User: {} Type: {}", userId, activityType);
        recordSecurityEvent("suspicious_activity", "warning");
        // Send alert to monitoring dashboard
    }

    // Rate limiting
    public boolean checkRateLimit(String key, int maxRequests, long timeWindowMs) {
        RateLimiter limiter = rateLimiters.computeIfAbsent(key, k -> new RateLimiter(maxRequests, timeWindowMs));
        return limiter.tryAcquire();
    }

    // Metrics retrieval
    public CompletableFuture<MonitoringMetrics> getMetrics() {
        MonitoringMetrics metrics = new MonitoringMetrics(
            messageCounter.labels("default").get(),
            activeUsers.get(),
            failedLogins.labels("unknown").get(),
            successfulLogins.labels("password").get(),
            systemMemoryUsage.get(),
            systemCpuUsage.get(),
            databaseConnections.get()
        );
        return CompletableFuture.completedFuture(metrics);
    }

    public CompletableFuture<Map<String, Object>> getDetailedMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        
        // Message metrics
        Map<String, Object> messageMetrics = new HashMap<>();
        messageMetrics.put("total", messageCounter.labels("default").get());
        messageMetrics.put("errors", messageErrorCounter.labels("unknown").get());
        metrics.put("messages", messageMetrics);
        
        // User metrics
        Map<String, Object> userMetrics = new HashMap<>();
        userMetrics.put("active", activeUsers.get());
        userMetrics.put("registrations", userRegistrations.get());
        userMetrics.put("deletions", userDeletions.get());
        metrics.put("users", userMetrics);
        
        // Authentication metrics
        Map<String, Object> authMetrics = new HashMap<>();
        authMetrics.put("failed_logins", failedLogins.labels("unknown").get());
        authMetrics.put("successful_logins", successfulLogins.labels("password").get());
        metrics.put("authentication", authMetrics);
        
        // System metrics
        Map<String, Object> systemMetrics = new HashMap<>();
        systemMetrics.put("memory_usage", systemMemoryUsage.get());
        systemMetrics.put("cpu_usage", systemCpuUsage.get());
        systemMetrics.put("database_connections", databaseConnections.get());
        metrics.put("system", systemMetrics);
        
        return CompletableFuture.completedFuture(metrics);
    }

    // Inner class for rate limiting
    private static class RateLimiter {
        private final int maxRequests;
        private final long timeWindowMs;
        private final long[] requestTimestamps;
        private int currentIndex = 0;

        public RateLimiter(int maxRequests, long timeWindowMs) {
            this.maxRequests = maxRequests;
            this.timeWindowMs = timeWindowMs;
            this.requestTimestamps = new long[maxRequests];
        }

        public synchronized boolean tryAcquire() {
            long currentTime = System.currentTimeMillis();
            long windowStart = currentTime - timeWindowMs;
            
            if (requestTimestamps[currentIndex] > windowStart) {
                return false;
            }
            
            requestTimestamps[currentIndex] = currentTime;
            currentIndex = (currentIndex + 1) % maxRequests;
            return true;
        }
    }
}
