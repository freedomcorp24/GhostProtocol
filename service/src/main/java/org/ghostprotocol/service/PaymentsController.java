/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.controllers;

import io.dropwizard.auth.Auth;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.auth.ExternalServiceCredentials;
import org.ghostprotocol.service.auth.ExternalServiceCredentialsGenerator;
import org.ghostprotocol.service.configuration.PaymentsServiceConfiguration;
import org.ghostprotocol.service.currency.CurrencyConversionManager;
import org.ghostprotocol.service.entities.CurrencyConversionEntityList;
import org.ghostprotocol.websocket.auth.ReadOnly;

@Path("/v1/payments")
@Tag(name = "Payments")
public class PaymentsController {

  private final ExternalServiceCredentialsGenerator paymentsServiceCredentialsGenerator;
  private final CurrencyConversionManager currencyManager;


  public static ExternalServiceCredentialsGenerator credentialsGenerator(final PaymentsServiceConfiguration cfg) {
    return ExternalServiceCredentialsGenerator
        .builder(cfg.userAuthenticationTokenSharedSecret())
        .prependUsername(true)
        .build();
  }

  public PaymentsController(final CurrencyConversionManager currencyManager,
      final ExternalServiceCredentialsGenerator paymentsServiceCredentialsGenerator) {
    this.currencyManager = currencyManager;
    this.paymentsServiceCredentialsGenerator = paymentsServiceCredentialsGenerator;
  }

  @GET
  @Path("/auth")
  @Produces(MediaType.APPLICATION_JSON)
  public ExternalServiceCredentials getAuth(final @ReadOnly @Auth AuthenticatedDevice auth) {
    return paymentsServiceCredentialsGenerator.generateForUuid(auth.getAccount().getUuid());
  }

  @GET
  @Path("/conversions")
  @Produces(MediaType.APPLICATION_JSON)
  public CurrencyConversionEntityList getConversions(final @ReadOnly @Auth AuthenticatedDevice auth) {
    return currencyManager.getCurrencyConversions().orElseThrow();
  }
}
