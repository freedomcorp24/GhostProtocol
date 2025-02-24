/*
 * Copyright 2013 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretBytes;

public record ZkConfig(@NotNull SecretBytes serverSecret,
                       @NotEmpty byte[] serverPublic) {
}
