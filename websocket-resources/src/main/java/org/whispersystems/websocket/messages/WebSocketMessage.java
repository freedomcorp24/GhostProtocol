/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.websocket.messages;

public interface WebSocketMessage {

  public enum Type {
    UNKNOWN_MESSAGE,
    REQUEST_MESSAGE,
    RESPONSE_MESSAGE
  }

  public Type                     getType();
  public WebSocketRequestMessage  getRequestMessage();
  public WebSocketResponseMessage getResponseMessage();
  public byte[]                   toByteArray();

}
