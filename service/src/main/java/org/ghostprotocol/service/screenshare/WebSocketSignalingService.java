package org.ghostprotocol.service.screenshare;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ghostprotocol.service.websocket.WebSocketConnection;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.push.PushNotificationManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketSignalingService {
    private final Map<String, WebSocketConnection> activeConnections = new ConcurrentHashMap<>();
    private final PushNotificationManager pushNotificationManager;
    private final ObjectMapper mapper;

    public WebSocketSignalingService(PushNotificationManager pushNotificationManager) {
        this.pushNotificationManager = pushNotificationManager;
        this.mapper = new ObjectMapper();
    }

    public void handleWebRTCSignal(String sessionId, String signal, SignalType type) {
        WebSocketConnection connection = activeConnections.get(sessionId);
        if (connection != null && connection.isConnected()) {
            try {
                WebRTCSignal webRTCSignal = new WebRTCSignal(type, signal);
                connection.sendString(mapper.writeValueAsString(webRTCSignal));
            } catch (Exception e) {
                // Log error and cleanup connection
                activeConnections.remove(sessionId);
                connection.close();
            }
        }
    }

    public void registerConnection(String sessionId, WebSocketConnection connection) {
        WebSocketConnection oldConnection = activeConnections.put(sessionId, connection);
        if (oldConnection != null) {
            oldConnection.close();
        }
    }

    public void removeConnection(String sessionId) {
        WebSocketConnection connection = activeConnections.remove(sessionId);
        if (connection != null) {
            connection.close();
        }
    }

    public void notifyScreenShareRequest(Account recipient, Account sender, String sessionId) {
        pushNotificationManager.sendScreenShareInvite(recipient, sender.getUsername(), sessionId);
    }

    public static class WebRTCSignal {
        private final SignalType type;
        private final String payload;

        public WebRTCSignal(SignalType type, String payload) {
            this.type = type;
            this.payload = payload;
        }

        public SignalType getType() {
            return type;
        }

        public String getPayload() {
            return payload;
        }
    }

    public enum SignalType {
        OFFER,
        ANSWER,
        ICE_CANDIDATE
    }
}
