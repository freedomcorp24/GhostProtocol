/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.ghostprotocol.configuration;

import java.time.Duration;

/**
 * Configuration for Device Check operations
 *
 * @param backupRedemptionDuration How long to grant backup access for redemptions via device check
 * @param backupRedemptionLevel    What backup level to grant redemptions via device check
 */
public record DeviceCheckConfiguration(Duration backupRedemptionDuration, long backupRedemptionLevel) {}
