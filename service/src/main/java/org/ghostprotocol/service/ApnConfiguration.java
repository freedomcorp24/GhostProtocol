/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ghostprotocol.service.configuration.secrets.SecretString;


public record ApnConfiguration(@NotNull SecretString teamId,
                               @NotNull SecretString keyId,
                               @NotNull SecretString signingKey,
                               @NotBlank String bundleId,
                               boolean sandbox) {
}
