package org.ghostprotocol.service.monitoring;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MonitoringService {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    private final Counter messageCounter = Counter.build()
        .name("messages_total")
        .help("Total messages processed")
        .register();

    private final Gauge activeUsers = Gauge.build()
        .name("active_users")
        .help("Number of currently active users")
        .register();

    private final Counter failedLogins = Counter.build()
        .name("failed_logins_total")
        .help("Total number of failed login attempts")
        .register();

    @Inject
    public MonitoringService() {}

    public void recordMessage() {
        messageCounter.inc();
    }

    public void recordUserActivity(boolean active) {
        if (active) {
            activeUsers.inc();
        } else {
            activeUsers.dec();
        }
    }

    public void recordFailedLogin() {
        failedLogins.inc();
    }

    public CompletableFuture<MonitoringMetrics> getMetrics() {
        MonitoringMetrics metrics = new MonitoringMetrics(
            messageCounter.get(),
            activeUsers.get(),
            failedLogins.get()
        );
        return CompletableFuture.completedFuture(metrics);
    }

    public void alertSuspiciousActivity(String userId, String activityType) {
        logger.warn("Suspicious activity detected - User: {} Type: {}", userId, activityType);
        // Send alert to monitoring dashboard
    }
}
