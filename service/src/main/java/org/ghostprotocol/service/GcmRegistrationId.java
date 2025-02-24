/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.ghostprotocol.entities;

import jakarta.validation.constraints.NotEmpty;

public record GcmRegistrationId(@NotEmpty String gcmRegistrationId) {
}
