/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.storage.devicecheck;

public class DeviceCheckVerificationFailedException extends Exception {

  public DeviceCheckVerificationFailedException(Exception cause) {
    super(cause);
  }

  public DeviceCheckVerificationFailedException(String s) {
    super(s);
  }
}
