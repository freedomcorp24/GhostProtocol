package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.entities.PaymentAddress;
import org.ghostprotocol.service.payments.PaymentProcessor;
import org.ghostprotocol.service.payments.ExchangeRateManager;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Path("/v1/payments/crypto")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentController {
  private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
  private static final Duration ADDRESS_EXPIRATION = Duration.ofHours(2);

  private final PaymentProcessor paymentProcessor;
  private final ExchangeRateManager exchangeRates;

  public PaymentController(PaymentProcessor paymentProcessor, ExchangeRateManager exchangeRates) {
    this.paymentProcessor = paymentProcessor;
    this.exchangeRates = exchangeRates;
  }

  @GET
  @Path("/rates")
  @Timed
  public Response getExchangeRates() {
    Map<String, BigDecimal> rates = Map.of(
        "BTC", exchangeRates.getRate("BTC"),
        "XMR", exchangeRates.getRate("XMR"),
        "USDT", exchangeRates.getRate("USDT")
    );
    return Response.ok(rates).build();
  }

  @POST
  @Path("/address/{currency}")
  @Timed
  public CompletableFuture<Response> generatePaymentAddress(
      @Auth AuthenticatedDevice auth,
      @PathParam("currency") @NotEmpty String currency) {
    
    try {
      return paymentProcessor.generatePaymentAddress(currency, auth.getAccount().getUuid())
          .thenApply(address -> {
            PaymentAddress response = new PaymentAddress(
                address, 
                Instant.now().plus(ADDRESS_EXPIRATION).getEpochSecond());
            return Response.ok(response).build();
          })
          .exceptionally(e -> {
            logger.error("Failed to generate {} payment address", currency, e);
            return Response.serverError()
                .entity(Map.of("error", "Failed to generate payment address"))
                .build();
          });
    } catch (IllegalArgumentException e) {
      return CompletableFuture.completedFuture(
          Response.status(Response.Status.BAD_REQUEST)
              .entity(Map.of("error", e.getMessage()))
              .build());
    }
  }

  @GET
  @Path("/check/{currency}/{address}")
  @Timed
  public CompletableFuture<Response> checkPayment(
      @Auth AuthenticatedDevice auth,
      @PathParam("currency") @NotEmpty String currency,
      @PathParam("address") @NotEmpty String address,
      @QueryParam("amount") @NotNull @Positive BigDecimal amount) {

    try {
      return paymentProcessor.checkPayment(currency, address, amount)
          .thenApply(received -> Response.ok(Map.of("received", received)).build())
          .exceptionally(e -> {
            logger.error("Failed to check {} payment for address {}", currency, address, e);
            return Response.serverError()
                .entity(Map.of("error", "Failed to check payment status"))
                .build();
          });
    } catch (IllegalArgumentException e) {
      return CompletableFuture.completedFuture(
          Response.status(Response.Status.BAD_REQUEST)
              .entity(Map.of("error", e.getMessage()))
              .build());
    }
  }
}
