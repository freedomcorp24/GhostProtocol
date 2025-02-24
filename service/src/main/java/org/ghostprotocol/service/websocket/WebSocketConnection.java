package org.ghostprotocol.service.websocket;

import org.ghostprotocol.websocket.messages.WebSocketMessage;

/**
 * Interface for WebSocket connections.
 * Provides methods for sending messages through WebSocket connections.
 */
public interface WebSocketConnection {
    
    /**
     * Sends a message through this connection
     * @param message The message to send
     */
    void send(String message);
    
    /**
     * Closes the connection
     */
    void close();
    
    /**
     * Closes the connection with a status code and reason
     * @param statusCode The status code
     * @param reason The reason for closing
     */
    void close(int statusCode, String reason);
    
    /**
     * Checks if the connection is open
     * @return true if the connection is open, false otherwise
     */
    boolean isOpen();
    
    /**
     * Gets the remote address of this connection
     * @return The remote address
     */
    String getRemoteAddress();
}
