/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import jakarta.validation.constraints.NotBlank;

public record SubmitVerificationCodeRequest(@NotBlank String code) {

}
