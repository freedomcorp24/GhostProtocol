/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import java.util.UUID;

public record LinkDeviceResponse(UUID uuid, UUID pni, byte deviceId) {
}
