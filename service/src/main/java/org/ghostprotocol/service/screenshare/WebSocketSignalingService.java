package org.ghostprotocol.service.screenshare;

import org.ghostprotocol.service.websocket.WebSocketHandler;
import javax.websocket.Session;

public class WebSocketSignalingService {
    private final WebSocketHandler webSocketHandler;

    public WebSocketSignalingService(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void handleSignal(SignalMessage message) {
        webSocketHandler.sendMessage(message);
    }
}
