package org.ghostprotocol.service.screenshare;

import org.ghostprotocol.websocket.WebSocketConnection;
import org.ghostprotocol.websocket.messages.WebSocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScreenShareService {
    private static final Logger logger = LoggerFactory.getLogger(ScreenShareService.class);
    private final Map<String, ScreenShareSession> activeSessions = new ConcurrentHashMap<>();

    public void startSession(String sessionId, WebSocketConnection connection, ScreenShareConfig config) {
        ScreenShareSession session = new ScreenShareSession(sessionId, connection, config);
        activeSessions.put(sessionId, session);
        session.start();
    }

    public void handleMessage(String sessionId, WebSocketMessage message) {
        ScreenShareSession session = activeSessions.get(sessionId);
        if (session != null) {
            session.handleMessage(message);
        }
    }

    public void stopSession(String sessionId) {
        ScreenShareSession session = activeSessions.remove(sessionId);
        if (session != null) {
            session.stop();
        }
    }
}
