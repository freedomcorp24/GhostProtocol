/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import static java.util.Objects.requireNonNull;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.Status;
import java.time.Clock;
import java.util.Map;
import org.ghostprotocol.chat.credentials.ExternalServiceType;
import org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest;
import org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse;
import org.ghostprotocol.chat.credentials.ReactorExternalServiceCredentialsGrpc;
import org.ghostprotocol.service.GhostProtocolServerConfiguration;
import org.ghostprotocol.service.auth.ExternalServiceCredentials;
import org.ghostprotocol.service.auth.ExternalServiceCredentialsGenerator;
import org.ghostprotocol.service.auth.grpc.AuthenticatedDevice;
import org.ghostprotocol.service.auth.grpc.AuthenticationUtil;
import org.ghostprotocol.service.limits.RateLimiters;
import reactor.core.publisher.Mono;

public class ExternalServiceCredentialsGrpcService extends ReactorExternalServiceCredentialsGrpc.ExternalServiceCredentialsImplBase {

  private final Map<ExternalServiceType, ExternalServiceCredentialsGenerator> credentialsGeneratorByType;

  private final RateLimiters rateLimiters;


  public static ExternalServiceCredentialsGrpcService createForAllExternalServices(
      final GhostProtocolServerConfiguration chatConfiguration,
      final RateLimiters rateLimiters) {
    return new ExternalServiceCredentialsGrpcService(
        ExternalServiceDefinitions.createExternalServiceList(chatConfiguration, Clock.systemUTC()),
        rateLimiters
    );
  }

  @VisibleForTesting
  ExternalServiceCredentialsGrpcService(
      final Map<ExternalServiceType, ExternalServiceCredentialsGenerator> credentialsGeneratorByType,
      final RateLimiters rateLimiters) {
    this.credentialsGeneratorByType = requireNonNull(credentialsGeneratorByType);
    this.rateLimiters = requireNonNull(rateLimiters);
  }

  @Override
  public Mono<GetExternalServiceCredentialsResponse> getExternalServiceCredentials(final GetExternalServiceCredentialsRequest request) {
    final ExternalServiceCredentialsGenerator credentialsGenerator = this.credentialsGeneratorByType
        .get(request.getExternalService());
    if (credentialsGenerator == null) {
      return Mono.error(Status.INVALID_ARGUMENT.asException());
    }
    final AuthenticatedDevice authenticatedDevice = AuthenticationUtil.requireAuthenticatedDevice();
    return rateLimiters.forDescriptor(RateLimiters.For.EXTERNAL_SERVICE_CREDENTIALS).validateReactive(authenticatedDevice.accountIdentifier())
        .then(Mono.fromSupplier(() -> {
          final ExternalServiceCredentials externalServiceCredentials = credentialsGenerator
              .generateForUuid(authenticatedDevice.accountIdentifier());
          return GetExternalServiceCredentialsResponse.newBuilder()
              .setUsername(externalServiceCredentials.username())
              .setPassword(externalServiceCredentials.password())
              .build();
        }));
  }
}
