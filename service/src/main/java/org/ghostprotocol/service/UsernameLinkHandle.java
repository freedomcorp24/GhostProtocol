/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UsernameLinkHandle(
    @Schema(description = "A handle that can be included in username links to retrieve the stored encrypted username")
    @NotNull
    UUID usernameLinkHandle) {
}
