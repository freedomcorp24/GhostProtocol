/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.util;

import io.dropwizard.util.DataSize;

public class Constants {
  public static final String METRICS_NAME = "ghostprotocol";
  public static final int MAXIMUM_STICKER_SIZE_BYTES = (int) DataSize.kibibytes(300 + 1).toBytes(); // add 1 kiB for encryption overhead
  public static final int MAXIMUM_STICKER_MANIFEST_SIZE_BYTES = (int) DataSize.kibibytes(10).toBytes();
}
