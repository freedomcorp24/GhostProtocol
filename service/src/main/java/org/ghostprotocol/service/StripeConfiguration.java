/*
 * Copyright 2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretBytes;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretString;
import org.ghostprotocol.ghostprotocol.subscriptions.PaymentMethod;

public record StripeConfiguration(@NotNull SecretString apiKey,
                                  @NotNull SecretBytes idempotencyKeyGenerator,
                                  @NotBlank String boostDescription,
                                  @Valid @NotEmpty Map<PaymentMethod, Set<@NotBlank String>> supportedCurrenciesByPaymentMethod) {
}
