/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.auth.grpc;

import java.util.UUID;

public record AuthenticatedDevice(UUID accountIdentifier, byte deviceId) {
}
