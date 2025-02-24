/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.configuration;

import org.ghostprotocol.service.configuration.secrets.SecretBytes;
import org.ghostprotocol.service.util.ExactlySize;

public record DirectoryV2ClientConfiguration(@ExactlySize(32) SecretBytes userAuthenticationTokenSharedSecret,
                                             @ExactlySize(32) SecretBytes userIdTokenSharedSecret) {
}
