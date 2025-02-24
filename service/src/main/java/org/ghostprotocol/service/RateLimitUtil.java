/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import org.ghostprotocol.service.limits.RateLimiter;
import reactor.core.publisher.Mono;

class RateLimitUtil {

  static Mono<Void> rateLimitByRemoteAddress(final RateLimiter rateLimiter) {
    return rateLimiter.validateReactive(RequestAttributesUtil.getRemoteAddress().getHostAddress());
  }
}
