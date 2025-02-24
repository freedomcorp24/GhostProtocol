/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.configuration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;
import io.lettuce.core.resource.ClientResources;
import org.ghostprotocol.service.redis.FaultTolerantRedisClusterClient;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = RedisClusterConfiguration.class)
public interface FaultTolerantRedisClusterFactory extends Discoverable {

  FaultTolerantRedisClusterClient build(String name, ClientResources.Builder clientResourcesBuilder);
}
