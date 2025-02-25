package org.ghostprotocol.service.analytics;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.ghostprotocol.service.monitoring.MonitoringService;

import java.time.Clock;

/**
 * Guice module for analytics-related services.
 */
public class AnalyticsModule extends AbstractModule {
    
    @Override
    protected void configure() {
        // Bind implementations to interfaces if needed
    }
    
    @Provides
    @Singleton
    public AnalyticsService provideAnalyticsService(MonitoringService monitoringService, Clock clock) {
        return new AnalyticsService(monitoringService, clock);
    }
    
    @Provides
    @Singleton
    public Clock provideClock() {
        return Clock.systemUTC();
    }
}
