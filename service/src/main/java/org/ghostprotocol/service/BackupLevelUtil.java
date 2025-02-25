/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.backup;

import org.ghostprotocol.zkgroup.backups.BackupLevel;

public class BackupLevelUtil {
  public static BackupLevel fromReceiptLevel(long receiptLevel) {
    try {
      return BackupLevel.fromValue(Math.toIntExact(receiptLevel));
    } catch (ArithmeticException e) {
      throw new IllegalArgumentException("Invalid receipt level: " + receiptLevel);
    }
  }
}
