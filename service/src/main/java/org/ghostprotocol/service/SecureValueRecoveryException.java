/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.securevaluerecovery;

public class SecureValueRecoveryException extends RuntimeException {
  private final String statusCode;

  public SecureValueRecoveryException(final String message, final String statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public String getStatusCode() {
    return statusCode;
  }
}
