/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.redis;

import io.lettuce.core.RedisURI;
import java.time.Duration;

public class RedisUriUtil {

  public static RedisURI createRedisUriWithTimeout(final String uri, final Duration timeout) {
    final RedisURI redisUri = RedisURI.create(uri);
    // for synchronous commands and the initial connection
    redisUri.setTimeout(timeout);
    return redisUri;
  }

}
