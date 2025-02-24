/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;
import io.micrometer.statsd.StatsdConfig;
import java.time.Duration;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = DogstatsdConfiguration.class)
public interface DatadogConfiguration extends StatsdConfig, Discoverable {

  String getEnvironment();

  Duration getShutdownWaitDuration();
}
