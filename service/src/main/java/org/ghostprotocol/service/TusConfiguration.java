/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.attachments;

import jakarta.validation.constraints.NotEmpty;
import org.ghostprotocol.service.configuration.secrets.SecretBytes;
import org.ghostprotocol.service.util.ExactlySize;

public record TusConfiguration(
  @ExactlySize(32) SecretBytes userAuthenticationTokenSharedSecret,
  @NotEmpty String uploadUri
){}
