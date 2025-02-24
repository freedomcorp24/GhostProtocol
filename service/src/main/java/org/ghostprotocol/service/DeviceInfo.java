/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ghostprotocol.ghostprotocol.storage.Device;
import org.ghostprotocol.ghostprotocol.util.ByteArrayBase64WithPaddingAdapter;

public record DeviceInfo(long id,

                         @JsonSerialize(using = ByteArrayBase64WithPaddingAdapter.Serializing.class)
                         @JsonDeserialize(using = ByteArrayBase64WithPaddingAdapter.Deserializing.class)
                         byte[] name,

                         long lastSeen,
                         long created) {

  public static DeviceInfo forDevice(final Device device) {
    return new DeviceInfo(device.getId(), device.getName(), device.getLastSeen(), device.getCreated());
  }
}
