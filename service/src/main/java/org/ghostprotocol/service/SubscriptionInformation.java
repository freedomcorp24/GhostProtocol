/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.subscriptions;

import java.time.Instant;
import javax.annotation.Nullable;

public record SubscriptionInformation(
    SubscriptionPrice price,
    long level,
    Instant billingCycleAnchor,
    Instant endOfCurrentPeriod,
    boolean active,
    boolean cancelAtPeriodEnd,
    SubscriptionStatus status,
    PaymentProvider paymentProvider,
    PaymentMethod paymentMethod,
    boolean paymentProcessing,
    @Nullable ChargeFailure chargeFailure) {}
