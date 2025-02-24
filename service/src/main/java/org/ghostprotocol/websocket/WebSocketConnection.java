package org.ghostprotocol.websocket;

import org.ghostprotocol.websocket.messages.WebSocketMessage;

/**
 * Interface for WebSocket connections.
 * Provides methods for sending messages through WebSocket connections.
 */
public interface WebSocketConnection {
    
    /**
     * Sends a WebSocket message through this connection
     * @param message The message to send
     */
    void send(WebSocketMessage message);
    
    /**
     * Checks if the connection is open
     * @return true if the connection is open, false otherwise
     */
    boolean isOpen();
    
    /**
     * Closes the WebSocket connection
     */
    void close();
    
    /**
     * Gets the user ID associated with this connection
     * @return The user ID
     */
    String getUserId();
}
