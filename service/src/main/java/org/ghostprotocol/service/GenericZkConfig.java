/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.configuration;

import jakarta.validation.constraints.NotNull;
import org.ghostprotocol.configuration.secrets.SecretBytes;

public record GenericZkConfig(@NotNull SecretBytes serverSecret) {
}
