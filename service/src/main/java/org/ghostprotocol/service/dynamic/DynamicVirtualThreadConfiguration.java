/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration.dynamic;

import java.util.Set;

public record DynamicVirtualThreadConfiguration(Set<String> allowedPinEvents) {}
