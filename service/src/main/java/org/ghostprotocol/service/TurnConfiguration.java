/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretBytes;

public record TurnConfiguration(SecretBytes secret, CloudflareTurnConfiguration cloudflare) {
}
