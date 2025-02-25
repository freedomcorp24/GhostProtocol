/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.backup;

import java.util.Map;

public record BackupUploadDescriptor(
    int cdn,
    String key,
    Map<String, String> headers,
    String signedUploadLocation) {}
