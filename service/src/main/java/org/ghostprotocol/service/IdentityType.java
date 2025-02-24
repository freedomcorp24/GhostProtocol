/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.identity;

public enum IdentityType {
  ACI((byte) 0x00, "ACI:"),
  PNI((byte) 0x01, "PNI:");

  private final byte bytePrefix;
  private final String stringPrefix;

  IdentityType(final byte bytePrefix, final String stringPrefix) {
    this.bytePrefix = bytePrefix;
    this.stringPrefix = stringPrefix;
  }

  byte getBytePrefix() {
    return bytePrefix;
  }

  String getStringPrefix() {
    return stringPrefix;
  }
}
