/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.push;

import java.time.Instant;
import java.util.Optional;

public record SendPushNotificationResult(boolean accepted,
                                         Optional<String> errorCode,
                                         boolean unregistered,
                                         Optional<Instant> unregisteredTimestamp) {
}
