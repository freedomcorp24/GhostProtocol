/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import io.grpc.Status;
import org.ghostprotocol.service.entities.AvatarChange;

public class AvatarChangeUtil {
  public static AvatarChange fromGrpcAvatarChange(final org.ghostprotocol.chat.profile.SetProfileRequest.AvatarChange avatarChangeType) {
    return switch (avatarChangeType) {
      case AVATAR_CHANGE_UNCHANGED -> AvatarChange.AVATAR_CHANGE_UNCHANGED;
      case AVATAR_CHANGE_CLEAR -> AvatarChange.AVATAR_CHANGE_CLEAR;
      case AVATAR_CHANGE_UPDATE -> AvatarChange.AVATAR_CHANGE_UPDATE;
      case UNRECOGNIZED -> throw Status.INVALID_ARGUMENT.withDescription("Invalid avatar change value").asRuntimeException();
    };
  }
}
