/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.websocket.auth;

public class AuthenticationException extends Exception {

  public AuthenticationException(String s) {
    super(s);
  }

  public AuthenticationException(Exception e) {
    super(e);
  }

}
