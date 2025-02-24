/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.ghostprotocol.ghostprotocol.controllers.ServerRejectedException;

public class ServerRejectedExceptionMapper implements ExceptionMapper<ServerRejectedException> {

  @Override
  public Response toResponse(final ServerRejectedException exception) {
    return Response.status(508).build();
  }
}
