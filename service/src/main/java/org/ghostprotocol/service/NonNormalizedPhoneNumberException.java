/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.util;

public class NonNormalizedPhoneNumberException extends Exception {

  private final String originalNumber;
  private final String normalizedNumber;

  public NonNormalizedPhoneNumberException(final String originalNumber, final String normalizedNumber) {
    this.originalNumber = originalNumber;
    this.normalizedNumber = normalizedNumber;
  }

  public String getOriginalNumber() {
    return originalNumber;
  }

  public String getNormalizedNumber() {
    return normalizedNumber;
  }
}
