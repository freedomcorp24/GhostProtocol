/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.websocket;

public class InvalidWebsocketAddressException extends Exception {
  public InvalidWebsocketAddressException(String serialized) {
    super(serialized);
  }

  public InvalidWebsocketAddressException(Exception e) {
    super(e);
  }
}
