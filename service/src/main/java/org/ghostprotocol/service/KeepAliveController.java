/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.controllers;

import static com.codahale.metrics.MetricRegistry.name;

import io.dropwizard.auth.Auth;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.metrics.UserAgentTagUtil;
import org.ghostprotocol.service.push.WebSocketConnectionEventManager;
import org.ghostprotocol.websocket.auth.ReadOnly;
import org.ghostprotocol.websocket.session.WebSocketSession;
import org.ghostprotocol.websocket.session.WebSocketSessionContext;


@Path("/v1/keepalive")
@Tag(name = "Keep Alive")
public class KeepAliveController {

  private final Logger logger = LoggerFactory.getLogger(KeepAliveController.class);

  private final WebSocketConnectionEventManager webSocketConnectionEventManager;

  private static final String CLOSED_CONNECTION_AGE_DISTRIBUTION_NAME = name(KeepAliveController.class,
      "closedConnectionAge");


  public KeepAliveController(final WebSocketConnectionEventManager webSocketConnectionEventManager) {
    this.webSocketConnectionEventManager = webSocketConnectionEventManager;
  }

  @GET
  public Response getKeepAlive(@ReadOnly @Auth Optional<AuthenticatedDevice> maybeAuth,
      @WebSocketSession WebSocketSessionContext context) {

    maybeAuth.ifPresent(auth -> {
      if (!webSocketConnectionEventManager.isLocallyPresent(auth.getAccount().getUuid(), auth.getAuthenticatedDevice().getId())) {

        final Duration age = Duration.between(context.getClient().getCreated(), Instant.now());

        logger.debug("***** No local subscription found for {}::{}; age = {}ms, User-Agent = {}",
            auth.getAccount().getUuid(), auth.getAuthenticatedDevice().getId(), age.toMillis(),
            context.getClient().getUserAgent());

        context.getClient().close(1000, "OK");

        Timer.builder(CLOSED_CONNECTION_AGE_DISTRIBUTION_NAME)
            .tags(Tags.of(UserAgentTagUtil.getPlatformTag(context.getClient().getUserAgent())))
            .publishPercentileHistogram(true)
            .register(Metrics.globalRegistry)
            .record(age);
      }
    });

    return Response.ok().build();
  }

  @GET
  @Path("/provisioning")
  public Response getProvisioningKeepAlive() {
    return Response.ok().build();
  }

}
