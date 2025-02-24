/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc.validators;

public record Range(int min, int max) {
  public Range {
    if (min > max) {
      throw new IllegalArgumentException("invalid range values: expected min <= max but have [%d, %d],".formatted(min, max));
    }
  }
}
