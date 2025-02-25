package org.ghostprotocol.service.storage;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.ghostprotocol.service.monitoring.MonitoringService;

import java.time.Clock;

/**
 * Guice module for storage usage tracking-related services.
 */
public class StorageUsageModule extends AbstractModule {
    
    private final String usageTableName;
    
    public StorageUsageModule(String usageTableName) {
        this.usageTableName = usageTableName;
    }
    
    @Override
    protected void configure() {
        // Bind implementations to interfaces if needed
    }
    
    @Provides
    @Singleton
    public StorageUsageTrackingService provideStorageUsageTrackingService(
            VaultStorageManager storageManager,
            AmazonDynamoDB dynamoDb,
            MonitoringService monitoringService,
            Clock clock) {
        return new StorageUsageTrackingService(
                storageManager,
                dynamoDb,
                usageTableName,
                monitoringService,
                clock
        );
    }
    
    @Provides
    @Singleton
    public Clock provideClock() {
        return Clock.systemUTC();
    }
}
