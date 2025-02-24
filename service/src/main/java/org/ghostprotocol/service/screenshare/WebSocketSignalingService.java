package org.ghostprotocol.service.screenshare;

import org.ghostprotocol.service.websocket.WebSocketHandler;
import org.ghostprotocol.websocket.messages.WebSocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket signaling service for screen sharing.
 * Handles WebRTC signaling between peers for screen sharing sessions.
 */
public class WebSocketSignalingService {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketSignalingService.class);
    private final WebSocketHandler webSocketHandler;
    private final Map<String, Session> activeSessions = new ConcurrentHashMap<>();
    private final ScreenShareService screenShareService;

    public WebSocketSignalingService(WebSocketHandler webSocketHandler, ScreenShareService screenShareService) {
        this.webSocketHandler = webSocketHandler;
        this.screenShareService = screenShareService;
    }

    /**
     * Handles incoming WebRTC signaling messages
     * @param message The signaling message
     */
    public void handleSignal(WebSocketMessage message) {
        String type = message.getType();
        String sessionId = message.getSessionId();
        
        logger.debug("Received signaling message of type: {} for session: {}", type, sessionId);
        
        switch (type) {
            case "offer":
                handleOffer(message);
                break;
            case "answer":
                handleAnswer(message);
                break;
            case "ice_candidate":
                handleIceCandidate(message);
                break;
            case "start_screen_share":
                handleStartScreenShare(message);
                break;
            case "stop_screen_share":
                handleStopScreenShare(message);
                break;
            default:
                // Forward to screen share service for handling
                screenShareService.handleMessage(sessionId, message);
        }
    }
    
    /**
     * Handles WebRTC offer messages
     */
    private void handleOffer(WebSocketMessage message) {
        String sessionId = message.getSessionId();
        String targetUserId = message.getTargetUserId();
        
        // Forward offer to target user
        WebSocketMessage forwardedOffer = new WebSocketMessage();
        forwardedOffer.setType("offer");
        forwardedOffer.setSessionId(sessionId);
        forwardedOffer.setSourceUserId(message.getSourceUserId());
        forwardedOffer.setSdp(message.getSdp());
        
        webSocketHandler.sendMessageToUser(targetUserId, forwardedOffer);
        logger.debug("Forwarded offer from {} to {} for session {}", 
                    message.getSourceUserId(), targetUserId, sessionId);
    }
    
    /**
     * Handles WebRTC answer messages
     */
    private void handleAnswer(WebSocketMessage message) {
        String sessionId = message.getSessionId();
        String targetUserId = message.getTargetUserId();
        
        // Forward answer to target user
        WebSocketMessage forwardedAnswer = new WebSocketMessage();
        forwardedAnswer.setType("answer");
        forwardedAnswer.setSessionId(sessionId);
        forwardedAnswer.setSourceUserId(message.getSourceUserId());
        forwardedAnswer.setSdp(message.getSdp());
        
        webSocketHandler.sendMessageToUser(targetUserId, forwardedAnswer);
        logger.debug("Forwarded answer from {} to {} for session {}", 
                    message.getSourceUserId(), targetUserId, sessionId);
    }
    
    /**
     * Handles ICE candidate messages
     */
    private void handleIceCandidate(WebSocketMessage message) {
        String sessionId = message.getSessionId();
        String targetUserId = message.getTargetUserId();
        
        // Forward ICE candidate to target user
        WebSocketMessage forwardedCandidate = new WebSocketMessage();
        forwardedCandidate.setType("ice_candidate");
        forwardedCandidate.setSessionId(sessionId);
        forwardedCandidate.setSourceUserId(message.getSourceUserId());
        forwardedCandidate.setIceCandidate(message.getIceCandidate());
        
        webSocketHandler.sendMessageToUser(targetUserId, forwardedCandidate);
        logger.debug("Forwarded ICE candidate from {} to {} for session {}", 
                    message.getSourceUserId(), targetUserId, sessionId);
    }
    
    /**
     * Handles start screen share requests
     */
    private void handleStartScreenShare(WebSocketMessage message) {
        String sessionId = message.getSessionId();
        String sourceUserId = message.getSourceUserId();
        String targetUserId = message.getTargetUserId();
        
        // Create default config
        ScreenShareConfig config = new ScreenShareConfig();
        
        // Apply any custom settings from the message
        if (message.getMaxBitrate() > 0) {
            config.setMaxBitrate(message.getMaxBitrate());
        }
        
        if (message.getMinBitrate() > 0) {
            config.setMinBitrate(message.getMinBitrate());
        }
        
        if (message.getFrameRate() > 0) {
            config.setFrameRate(message.getFrameRate());
        }
        
        if (message.getVideoCodec() != null && !message.getVideoCodec().isEmpty()) {
            config.setVideoCodec(message.getVideoCodec());
        }
        
        // Notify target user about incoming screen share
        WebSocketMessage notification = new WebSocketMessage();
        notification.setType("incoming_screen_share");
        notification.setSessionId(sessionId);
        notification.setSourceUserId(sourceUserId);
        
        webSocketHandler.sendMessageToUser(targetUserId, notification);
        logger.info("Notified user {} about incoming screen share from {} for session {}", 
                   targetUserId, sourceUserId, sessionId);
        
        // Start screen share session
        screenShareService.startSession(sessionId, webSocketHandler.getConnection(sourceUserId), config);
    }
    
    /**
     * Handles stop screen share requests
     */
    private void handleStopScreenShare(WebSocketMessage message) {
        String sessionId = message.getSessionId();
        String targetUserId = message.getTargetUserId();
        
        // Stop the screen share session
        screenShareService.stopSession(sessionId);
        
        // Notify target user that screen share has ended
        WebSocketMessage notification = new WebSocketMessage();
        notification.setType("screen_share_ended");
        notification.setSessionId(sessionId);
        notification.setSourceUserId(message.getSourceUserId());
        
        webSocketHandler.sendMessageToUser(targetUserId, notification);
        logger.info("Screen share session {} ended by user {}", 
                   sessionId, message.getSourceUserId());
    }
    
    /**
     * Registers a new WebSocket session
     */
    public void registerSession(String userId, Session session) {
        activeSessions.put(userId, session);
        logger.debug("Registered WebSocket session for user: {}", userId);
    }
    
    /**
     * Unregisters a WebSocket session
     */
    public void unregisterSession(String userId) {
        activeSessions.remove(userId);
        logger.debug("Unregistered WebSocket session for user: {}", userId);
    }
}
