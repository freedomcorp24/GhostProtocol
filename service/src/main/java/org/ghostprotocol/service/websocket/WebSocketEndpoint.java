package org.ghostprotocol.service.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/v1/websocket", configurator = WebSocketConfigurator.class)
public class WebSocketEndpoint {
    private final WebSocketConnectionFactory connectionFactory;
    private WebSocketConnection connection;

    public WebSocketEndpoint(WebSocketConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @OnOpen
    public void onOpen(Session session) {
        String sessionId = session.getPathParameters().get("sessionId");
        String authToken = session.getPathParameters().get("token");

        // Create appropriate WebSocket connection based on session type
        if (sessionId != null) {
            connection = connectionFactory.createScreenShareConnection(null, sessionId); // Auth will be handled by handler
        } else {
            session.close();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        if (connection != null) {
            connection.onWebSocketText(message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        if (connection != null) {
            connection.onWebSocketClose(1000, "Normal closure");
            connection = null;
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        if (connection != null) {
            connection.onWebSocketError(throwable);
        }
    }
}
