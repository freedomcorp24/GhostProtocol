/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record RemoteConfigConfiguration(@NotNull Map<String, String> globalConfig) {

}
