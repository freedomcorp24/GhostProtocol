/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.controllers;

import java.util.List;


public class StaleDevicesException extends Exception {

  private final List<Byte> staleDevices;

  public StaleDevicesException(List<Byte> staleDevices) {
    this.staleDevices = staleDevices;
  }

  public List<Byte> getStaleDevices() {
    return staleDevices;
  }
}
