/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import io.grpc.Status;
import org.ghostprotocol.service.storage.Device;

public class DeviceIdUtil {

  static byte validate(int deviceId) {
    if (deviceId < Device.PRIMARY_ID || deviceId > Byte.MAX_VALUE) {
      throw Status.INVALID_ARGUMENT.withDescription("Device ID is out of range").asRuntimeException();
    }

    return (byte) deviceId;
  }
}
