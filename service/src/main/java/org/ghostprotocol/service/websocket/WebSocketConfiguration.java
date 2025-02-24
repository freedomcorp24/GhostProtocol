package org.ghostprotocol.service.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebSocketConfiguration {
    @JsonProperty
    private int maxTextMessageSize = 64 * 1024; // 64KB

    @JsonProperty
    private int maxBinaryMessageSize = 64 * 1024; // 64KB

    @JsonProperty
    private int idleTimeout = 300000; // 5 minutes

    @JsonProperty
    private int maxConnections = 1000;

    public int getMaxTextMessageSize() {
        return maxTextMessageSize;
    }

    public int getMaxBinaryMessageSize() {
        return maxBinaryMessageSize;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public int getMaxConnections() {
        return maxConnections;
    }
}
