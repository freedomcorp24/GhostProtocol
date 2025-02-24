/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.calls.routing;

import java.util.List;
import java.util.Optional;

public record TurnServerOptions(
    String hostname,
    Optional<List<String>> urlsWithIps,
    Optional<List<String>> urlsWithHostname
) {
}
