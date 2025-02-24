/*
 * Copyright 2023 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.configuration;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;

public record MessageByteLimitCardinalityEstimatorConfiguration(@NotNull Duration period) {}
