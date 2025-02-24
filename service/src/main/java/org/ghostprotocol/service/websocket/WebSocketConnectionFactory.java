package org.ghostprotocol.service.websocket;

import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.screenshare.ScreenShareService;

public class WebSocketConnectionFactory {
    private final ScreenShareService screenShareService;

    public WebSocketConnectionFactory(ScreenShareService screenShareService) {
        this.screenShareService = screenShareService;
    }

    public WebSocketConnection createScreenShareConnection(AuthenticatedDevice auth, String sessionId) {
        WebSocketHandler handler = new ScreenShareWebSocketHandler(screenShareService, auth, sessionId);
        return new WebSocketConnection(handler);
    }
}
