/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.auth;


import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

public class InvalidAuthorizationHeaderException extends WebApplicationException {
  public InvalidAuthorizationHeaderException(String s) {
    super(s, Status.UNAUTHORIZED);
  }

  public InvalidAuthorizationHeaderException(Exception e) {
    super(e, Status.UNAUTHORIZED);
  }
}
