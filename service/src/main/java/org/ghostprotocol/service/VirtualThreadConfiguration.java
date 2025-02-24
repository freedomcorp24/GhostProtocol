/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.ghostprotocol.configuration;

import java.time.Duration;

public record VirtualThreadConfiguration(Duration pinEventThreshold) {}
