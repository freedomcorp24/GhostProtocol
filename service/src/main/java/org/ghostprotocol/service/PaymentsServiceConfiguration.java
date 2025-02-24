/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretBytes;

public record PaymentsServiceConfiguration(@NotNull SecretBytes userAuthenticationTokenSharedSecret,
                                           @NotEmpty List<String> paymentCurrencies,
                                           @NotNull @Valid PaymentsServiceClientsFactory externalClients) {


}
