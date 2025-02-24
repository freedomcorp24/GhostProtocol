/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.storage;

public class ChunkProcessingFailedException extends Exception {

  public ChunkProcessingFailedException(String message) {
    super(message);
  }

  public ChunkProcessingFailedException(Exception cause) {
    super(cause);
  }
}
