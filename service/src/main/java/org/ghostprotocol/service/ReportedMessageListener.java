/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.storage;

import java.util.Optional;
import java.util.UUID;

public interface ReportedMessageListener {

  void handleMessageReported(String sourceNumber, UUID messageGuid, UUID reporterUuid, Optional<byte[]> reportSpamToken);
}
