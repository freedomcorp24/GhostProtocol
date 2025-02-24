/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.limits;

import jakarta.validation.constraints.AssertTrue;
import java.time.Duration;

public record RateLimiterConfig(int bucketSize, Duration permitRegenerationDuration) {

  public double leakRatePerMillis() {
    return 1.0 / (permitRegenerationDuration.toNanos() / 1e6);
  }

  @AssertTrue
  public boolean hasPositiveRegenerationRate() {
    try {
      return permitRegenerationDuration.toNanos() > 0;
    } catch (final ArithmeticException e) {
      // The duration was too large to fit in a long, so it's definitely positive
      return true;
    }
  }
}
