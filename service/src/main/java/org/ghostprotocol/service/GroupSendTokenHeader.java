/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.auth;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import java.util.Base64;
import org.signal.libsignal.zkgroup.InvalidInputException;
import org.signal.libsignal.zkgroup.groupsend.GroupSendFullToken;

public record GroupSendTokenHeader(GroupSendFullToken token) {

  public static GroupSendTokenHeader valueOf(String header) {
    try {
      return new GroupSendTokenHeader(new GroupSendFullToken(Base64.getDecoder().decode(header)));
    } catch (InvalidInputException | IllegalArgumentException e) {
      // Base64 throws IllegalArgumentException; GroupSendFullToken ctor throws InvalidInputException
      throw new WebApplicationException(e, Status.UNAUTHORIZED);
    }
  }

}
