/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import io.grpc.Status;
import org.ghostprotocol.service.storage.DeviceCapability;

public class DeviceCapabilityUtil {

  private DeviceCapabilityUtil() {
  }

  public static DeviceCapability fromGrpcDeviceCapability(final org.ghostprotocol.chat.common.DeviceCapability grpcDeviceCapability) {
    return switch (grpcDeviceCapability) {
      case DEVICE_CAPABILITY_STORAGE -> DeviceCapability.STORAGE;
      case DEVICE_CAPABILITY_TRANSFER -> DeviceCapability.TRANSFER;
      case DEVICE_CAPABILITY_DELETE_SYNC -> DeviceCapability.DELETE_SYNC;
      case DEVICE_CAPABILITY_VERSIONED_EXPIRATION_TIMER -> DeviceCapability.VERSIONED_EXPIRATION_TIMER;
      case DEVICE_CAPABILITY_STORAGE_SERVICE_RECORD_KEY_ROTATION -> DeviceCapability.STORAGE_SERVICE_RECORD_KEY_ROTATION;
      case DEVICE_CAPABILITY_UNSPECIFIED, UNRECOGNIZED -> throw Status.INVALID_ARGUMENT.withDescription("Unrecognized device capability").asRuntimeException();
    };
  }

  public static org.ghostprotocol.chat.common.DeviceCapability toGrpcDeviceCapability(final DeviceCapability deviceCapability) {
    return switch (deviceCapability) {
      case STORAGE -> org.ghostprotocol.chat.common.DeviceCapability.DEVICE_CAPABILITY_STORAGE;
      case TRANSFER -> org.ghostprotocol.chat.common.DeviceCapability.DEVICE_CAPABILITY_TRANSFER;
      case DELETE_SYNC -> org.ghostprotocol.chat.common.DeviceCapability.DEVICE_CAPABILITY_DELETE_SYNC;
      case VERSIONED_EXPIRATION_TIMER -> org.ghostprotocol.chat.common.DeviceCapability.DEVICE_CAPABILITY_VERSIONED_EXPIRATION_TIMER;
      case STORAGE_SERVICE_RECORD_KEY_ROTATION -> org.ghostprotocol.chat.common.DeviceCapability.DEVICE_CAPABILITY_STORAGE_SERVICE_RECORD_KEY_ROTATION;
    };
  }
}
