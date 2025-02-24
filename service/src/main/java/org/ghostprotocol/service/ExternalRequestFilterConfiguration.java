/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import org.ghostprotocol.ghostprotocol.util.InetAddressRange;

public record ExternalRequestFilterConfiguration(@Valid @NotNull Set<@NotNull String> paths,
                                                 @Valid @NotNull Set<@NotNull InetAddressRange> permittedInternalRanges,
                                                 @Valid @NotNull Set<@NotNull String> grpcMethods) {
}
