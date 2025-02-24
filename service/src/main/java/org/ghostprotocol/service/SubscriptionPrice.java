/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.subscriptions;

import java.math.BigDecimal;

public record SubscriptionPrice(String currency, BigDecimal amount) {}
