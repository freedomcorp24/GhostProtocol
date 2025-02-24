package org.ghostprotocol.service.monitoring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoringMetrics {
    private final double messagesProcessed;
    private final double activeUserCount;
    private final double failedLoginAttempts;

    public MonitoringMetrics(
            @JsonProperty("messagesProcessed") double messagesProcessed,
            @JsonProperty("activeUserCount") double activeUserCount,
            @JsonProperty("failedLoginAttempts") double failedLoginAttempts) {
        this.messagesProcessed = messagesProcessed;
        this.activeUserCount = activeUserCount;
        this.failedLoginAttempts = failedLoginAttempts;
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
}
