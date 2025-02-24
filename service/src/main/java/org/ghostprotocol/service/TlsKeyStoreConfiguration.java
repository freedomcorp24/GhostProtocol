/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.constraints.NotNull;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretString;

public record TlsKeyStoreConfiguration(@NotNull SecretString password) {
}
