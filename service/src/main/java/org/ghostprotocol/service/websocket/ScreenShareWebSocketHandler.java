package org.ghostprotocol.service.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ghostprotocol.service.screenshare.ScreenShareService;
import org.ghostprotocol.service.screenshare.WebSocketSignalingService.SignalType;
import org.ghostprotocol.service.auth.AuthenticatedDevice;

public class ScreenShareWebSocketHandler extends WebSocketHandler {
    private final ScreenShareService screenShareService;
    private final ObjectMapper mapper;
    private final String sessionId;

    public ScreenShareWebSocketHandler(ScreenShareService screenShareService,
                                     AuthenticatedDevice auth,
                                     String sessionId) {
        super(auth);
        this.screenShareService = screenShareService;
        this.mapper = new ObjectMapper();
        this.sessionId = sessionId;
    }

    @Override
    public void onWebSocketConnect(WebSocketConnection connection) {
        super.onWebSocketConnect(connection);
        screenShareService.registerConnection(sessionId, connection);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        screenShareService.stopScreenShare(sessionId);
        super.onWebSocketClose(statusCode, reason);
    }

    @Override
    protected void handleWebSocketMessage(String message) {
        try {
            WebSocketMessage msg = mapper.readValue(message, WebSocketMessage.class);
            
            switch (msg.type) {
                case "ice_candidate":
                    screenShareService.handleIceCandidate(sessionId, msg.payload);
                    break;
                case "offer":
                    screenShareService.handleOffer(sessionId, msg.payload);
                    break;
                case "answer":
                    screenShareService.handleAnswer(sessionId, msg.payload);
                    break;
                default:
                    connection.close(1003, "Unsupported message type");
            }
        } catch (Exception e) {
            connection.close(1003, "Invalid message format");
        }
    }

    private static class WebSocketMessage {
        public String type;
        public String payload;
    }
}
