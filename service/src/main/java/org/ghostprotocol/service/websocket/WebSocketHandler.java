package org.ghostprotocol.service.websocket;

import org.ghostprotocol.service.auth.AuthenticatedDevice;

public abstract class WebSocketHandler {
    protected final AuthenticatedDevice auth;
    protected WebSocketConnection connection;

    protected WebSocketHandler(AuthenticatedDevice auth) {
        this.auth = auth;
    }

    public void onWebSocketConnect(WebSocketConnection connection) {
        this.connection = connection;
    }

    public void onWebSocketClose(int statusCode, String reason) {
        this.connection = null;
    }

    public void onWebSocketError(Throwable error) {
        if (connection != null) {
            connection.close(1011, "Internal error");
        }
    }

    public void onWebSocketMessage(String message) {
        if (connection != null) {
            handleWebSocketMessage(message);
        }
    }

    protected abstract void handleWebSocketMessage(String message);
}
