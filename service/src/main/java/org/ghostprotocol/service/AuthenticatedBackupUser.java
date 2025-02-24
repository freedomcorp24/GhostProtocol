/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.auth;

import org.signal.libsignal.zkgroup.backups.BackupCredentialType;
import org.signal.libsignal.zkgroup.backups.BackupLevel;

public record AuthenticatedBackupUser(byte[] backupId,
                                      BackupCredentialType credentialType,
                                      BackupLevel backupLevel,
                                      String backupDir,
                                      String mediaDir) {
}
