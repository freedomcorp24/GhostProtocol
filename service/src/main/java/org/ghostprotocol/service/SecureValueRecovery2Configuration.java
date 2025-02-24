/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretBytes;
import org.ghostprotocol.ghostprotocol.util.ExactlySize;

public record SecureValueRecovery2Configuration(
    @NotBlank String uri,
    @ExactlySize(32) SecretBytes userAuthenticationTokenSharedSecret,
    @ExactlySize(32) SecretBytes userIdTokenSharedSecret,
    @NotEmpty List<@NotBlank String> svrCaCertificates,
    @NotNull @Valid CircuitBreakerConfiguration circuitBreaker,
    @NotNull @Valid RetryConfiguration retry) {

  public SecureValueRecovery2Configuration {
    if (circuitBreaker == null) {
      circuitBreaker = new CircuitBreakerConfiguration();
    }

    if (retry == null) {
      retry = new RetryConfiguration();
    }
  }
}
