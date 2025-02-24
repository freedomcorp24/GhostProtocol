/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.textsecuregcm.controllers;

import static org.ghostprotocol.textsecuregcm.metrics.MetricsUtil.name;

import com.google.common.net.HttpHeaders;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import org.ghostprotocol.textsecuregcm.auth.BasicAuthorizationHeader;
// Removed phone verification imports
import org.ghostprotocol.textsecuregcm.auth.RegistrationLockVerificationManager;
import org.ghostprotocol.textsecuregcm.entities.AccountIdentityResponse;
// Removed phone verification imports
import org.ghostprotocol.textsecuregcm.entities.RegistrationLockFailure;
import org.ghostprotocol.textsecuregcm.entities.RegistrationRequest;
import org.ghostprotocol.textsecuregcm.limits.RateLimiters;
import org.ghostprotocol.textsecuregcm.metrics.UserAgentTagUtil;
import org.ghostprotocol.textsecuregcm.storage.Account;
import org.ghostprotocol.textsecuregcm.storage.AccountsManager;
import org.ghostprotocol.textsecuregcm.storage.DeviceCapability;
import org.ghostprotocol.textsecuregcm.storage.DeviceSpec;
import org.ghostprotocol.textsecuregcm.util.HeaderUtils;
import org.ghostprotocol.textsecuregcm.util.Util;

@Path("/v1/registration")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Registration")
public class RegistrationController {

  private static final DistributionSummary REREGISTRATION_IDLE_DAYS_DISTRIBUTION = DistributionSummary
      .builder(name(RegistrationController.class, "reregistrationIdleDays"))
      .publishPercentiles(0.75, 0.95, 0.99, 0.999)
      .distributionStatisticExpiry(Duration.ofHours(2))
      .register(Metrics.globalRegistry);

  private static final String ACCOUNT_CREATED_COUNTER_NAME = name(RegistrationController.class, "accountCreated");
  private static final String USERNAME_LENGTH_TAG_NAME = "usernameLength";
  private static final String VERIFICATION_TYPE_TAG_NAME = "verification";
  private static final String INVALID_ACCOUNT_ATTRS_COUNTER_NAME = name(RegistrationController.class, "invalidAccountAttrs");

  private final AccountsManager accounts;
  private final RegistrationLockVerificationManager registrationLockVerificationManager;
  private final RateLimiters rateLimiters;

  private byte[] hashUsername(String username) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return digest.digest(username.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("SHA-256 not available", e);
    }
  }

  public RegistrationController(final AccountsManager accounts,
                                final RegistrationLockVerificationManager registrationLockVerificationManager,
                                final RateLimiters rateLimiters) {

    this.accounts = accounts;
    this.registrationLockVerificationManager = registrationLockVerificationManager;
    this.rateLimiters = rateLimiters;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Registers an account",
  description = """
      Registers a new account or attempts to “re-register” an existing account. It is expected that a well-behaved client
      could make up to three consecutive calls to this API:
      1. gets 423 from existing registration lock \n
      2. gets 409 from device available for transfer \n
      3. success \n
      """)
  @ApiResponse(responseCode = "200", description = "The phone number associated with the authenticated account was changed successfully", useReturnTypeSchema = true)
  @ApiResponse(responseCode = "403", description = "Verification failed for the provided Registration Recovery Password")
  @ApiResponse(responseCode = "409", description = "The caller has not explicitly elected to skip transferring data from another device, but a device transfer is technically possible")
  @ApiResponse(responseCode = "422", description = "The request did not pass validation")
  @ApiResponse(responseCode = "423", content = @Content(schema = @Schema(implementation = RegistrationLockFailure.class)))
  @ApiResponse(responseCode = "429", description = "Too many attempts", headers = @Header(
      name = "Retry-After",
      description = "If present, an positive integer indicating the number of seconds before a subsequent attempt could succeed"))
  public AccountIdentityResponse register(
      @HeaderParam(HttpHeaders.AUTHORIZATION) @NotNull final BasicAuthorizationHeader authorizationHeader,
      @HeaderParam(HeaderUtils.X_SIGNAL_AGENT) final String signalAgent,
      @HeaderParam(HttpHeaders.USER_AGENT) final String userAgent,
      @NotNull @Valid final UsernameRegistrationRequest registrationRequest,
      @Context final ContainerRequestContext requestContext) throws RateLimitExceededException, InterruptedException {

    final String username = registrationRequest.getUsername();
    final String publicUsername = registrationRequest.getPublicUsername();
    final String password = authorizationHeader.getPassword();

    if (!registrationRequest.isEverySignedKeyValid(userAgent)) {
      throw new WebApplicationException("Invalid signature", 422);
    }

    rateLimiters.getRegistrationLimiter().validate(username);

    byte[] usernameHash = hashUsername(username);
    byte[] publicUsernameHash = hashUsername(publicUsername);

    final Optional<Account> existingAccount = accounts.getByUsernameHash(usernameHash);

    existingAccount.ifPresent(account -> {
      final Instant accountLastSeen = Instant.ofEpochMilli(account.getLastSeen());
      final Duration timeSinceLastSeen = Duration.between(accountLastSeen, Instant.now());
      REREGISTRATION_IDLE_DAYS_DISTRIBUTION.record(timeSinceLastSeen.toDays());
    });

    if (!registrationRequest.skipDeviceTransfer() && existingAccount.map(account -> account.hasCapability(DeviceCapability.TRANSFER)).orElse(false)) {
      // If a device transfer is possible, clients must explicitly opt out of a transfer (i.e. after prompting the user)
      // before we'll let them create a new account "from scratch"
      throw new WebApplicationException(Response.status(409, "device transfer available").build());
    }

    if (existingAccount.isPresent()) {
      registrationLockVerificationManager.verifyRegistrationLock(existingAccount.get(),
          registrationRequest.accountAttributes().getRegistrationLock(),
          userAgent, RegistrationLockVerificationManager.Flow.REGISTRATION, "username");
    }

    final Account account = accounts.create(usernameHash, publicUsernameHash,
        registrationRequest.accountAttributes(),
        existingAccount.map(Account::getBadges).orElseGet(ArrayList::new),
        registrationRequest.aciIdentityKey(),
        registrationRequest.pniIdentityKey(),
        new DeviceSpec(
            registrationRequest.accountAttributes().getName(),
            password,
            signalAgent,
            registrationRequest.accountAttributes().getCapabilities(),
            registrationRequest.accountAttributes().getRegistrationId(),
            registrationRequest.accountAttributes().getPhoneNumberIdentityRegistrationId(),
            registrationRequest.accountAttributes().getFetchesMessages(),
            registrationRequest.deviceActivationRequest().apnToken(),
            registrationRequest.deviceActivationRequest().gcmToken(),
            registrationRequest.deviceActivationRequest().aciSignedPreKey(),
            registrationRequest.deviceActivationRequest().pniSignedPreKey(),
            registrationRequest.deviceActivationRequest().aciPqLastResortPreKey(),
            registrationRequest.deviceActivationRequest().pniPqLastResortPreKey()),
        userAgent);

    Metrics.counter(ACCOUNT_CREATED_COUNTER_NAME, Tags.of(UserAgentTagUtil.getPlatformTag(userAgent),
            Tag.of(USERNAME_LENGTH_TAG_NAME, String.valueOf(username.length())),
            Tag.of(VERIFICATION_TYPE_TAG_NAME, "username")))
        .increment();

    return new AccountIdentityResponseBuilder(account)
        // If there was an existing account, return whether it could have had something in the storage service
        .storageCapable(existingAccount
            .map(a -> a.hasCapability(DeviceCapability.STORAGE))
            .orElse(false))
        .build();
  }

}
