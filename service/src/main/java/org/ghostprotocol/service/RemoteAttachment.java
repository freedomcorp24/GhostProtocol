/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.ghostprotocol.ghostprotocol.util.ValidBase64URLString;

public record RemoteAttachment(
    @Schema(description = "The attachment cdn")
    @NotNull
    Integer cdn,

    @NotBlank
    @ValidBase64URLString
    @Size(max = 64)
    @Schema(description = "The attachment key", maxLength = 64)
    String key) implements TransferArchiveResult {}
