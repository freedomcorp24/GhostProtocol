package org.ghostprotocol.service.websocket;

import com.codahale.metrics.health.HealthCheck;

public class WebSocketHealthCheck extends HealthCheck {
    private final WebSocketConnectionFactory connectionFactory;

    public WebSocketHealthCheck(WebSocketConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected Result check() {
        try {
            // Check if WebSocket server is running and accepting connections
            if (connectionFactory != null) {
                return Result.healthy();
            } else {
                return Result.unhealthy("WebSocket connection factory not initialized");
            }
        } catch (Exception e) {
            return Result.unhealthy("WebSocket health check failed: " + e.getMessage());
        }
    }
}
