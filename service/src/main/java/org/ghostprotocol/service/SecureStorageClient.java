/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.securestorage;

import static org.ghostprotocol.service.util.HeaderUtils.basicAuthHeader;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import org.ghostprotocol.service.auth.ExternalServiceCredentials;
import org.ghostprotocol.service.auth.ExternalServiceCredentialsGenerator;
import org.ghostprotocol.service.configuration.SecureStorageServiceConfiguration;
import org.ghostprotocol.service.http.FaultTolerantHttpClient;
import org.ghostprotocol.service.util.HttpUtils;

/**
 * A client for sending requests to GhostProtocol's secure storage service on behalf of authenticated users.
 */
public class SecureStorageClient {

  private final ExternalServiceCredentialsGenerator storageServiceCredentialsGenerator;
  private final URI deleteUri;
  private final FaultTolerantHttpClient httpClient;

  @VisibleForTesting
  static final String DELETE_PATH = "/v1/storage";

  public SecureStorageClient(final ExternalServiceCredentialsGenerator storageServiceCredentialsGenerator,
      final Executor executor, final
  ScheduledExecutorService retryExecutor, final SecureStorageServiceConfiguration configuration)
      throws CertificateException {
    this.storageServiceCredentialsGenerator = storageServiceCredentialsGenerator;
    this.deleteUri = URI.create(configuration.uri()).resolve(DELETE_PATH);
    this.httpClient = FaultTolerantHttpClient.newBuilder()
        .withCircuitBreaker(configuration.circuitBreaker())
        .withRetry(configuration.retry())
        .withRetryExecutor(retryExecutor)
        .withVersion(HttpClient.Version.HTTP_1_1)
        .withConnectTimeout(Duration.ofSeconds(10))
        .withRedirect(HttpClient.Redirect.NEVER)
        .withExecutor(executor)
        .withName("secure-storage")
        .withSecurityProtocol(FaultTolerantHttpClient.SECURITY_PROTOCOL_TLS_1_3)
        .withTrustedServerCertificates(configuration.storageCaCertificates().toArray(new String[0]))
        .build();
  }

  public CompletableFuture<Void> deleteStoredData(final UUID accountUuid) {
    final ExternalServiceCredentials credentials = storageServiceCredentialsGenerator.generateForUuid(accountUuid);

    final HttpRequest request = HttpRequest.newBuilder()
        .uri(deleteUri)
        .DELETE()
        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader(credentials))
        .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> {
            if (HttpUtils.isSuccessfulResponse(response.statusCode())) {
                return null;
            }

            throw new SecureStorageException("Failed to delete storage service data: " + response.statusCode());
        });
    }
}
