package org.ghostprotocol.service.monitoring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoringMetrics {
    private final double messagesProcessed;
    private final double activeUserCount;
    private final double failedLoginAttempts;
    private final double successfulLogins;
    private final double systemMemoryUsage;
    private final double systemCpuUsage;
    private final double databaseConnections;

    public MonitoringMetrics(
            @JsonProperty("messagesProcessed") double messagesProcessed,
            @JsonProperty("activeUserCount") double activeUserCount,
            @JsonProperty("failedLoginAttempts") double failedLoginAttempts,
            @JsonProperty("successfulLogins") double successfulLogins,
            @JsonProperty("systemMemoryUsage") double systemMemoryUsage,
            @JsonProperty("systemCpuUsage") double systemCpuUsage,
            @JsonProperty("databaseConnections") double databaseConnections) {
        this.messagesProcessed = messagesProcessed;
        this.activeUserCount = activeUserCount;
        this.failedLoginAttempts = failedLoginAttempts;
        this.successfulLogins = successfulLogins;
        this.systemMemoryUsage = systemMemoryUsage;
        this.systemCpuUsage = systemCpuUsage;
        this.databaseConnections = databaseConnections;
    }

    @JsonProperty
    public double getMessagesProcessed() {
        return messagesProcessed;
    }

    @JsonProperty
    public double getActiveUserCount() {
        return activeUserCount;
    }

    @JsonProperty
    public double getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    @JsonProperty
    public double getSuccessfulLogins() {
        return successfulLogins;
    }

    @JsonProperty
    public double getSystemMemoryUsage() {
        return systemMemoryUsage;
    }

    @JsonProperty
    public double getSystemCpuUsage() {
        return systemCpuUsage;
    }

    @JsonProperty
    public double getDatabaseConnections() {
        return databaseConnections;
    }
}
