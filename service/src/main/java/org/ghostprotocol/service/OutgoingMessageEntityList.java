/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import java.util.List;

public record OutgoingMessageEntityList(List<OutgoingMessageEntity> messages, boolean more) {
}
