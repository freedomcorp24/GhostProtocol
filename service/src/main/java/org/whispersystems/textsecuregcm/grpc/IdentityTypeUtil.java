/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.grpc;

import io.grpc.Status;
import org.whispersystems.textsecuregcm.identity.IdentityType;

public class IdentityTypeUtil {

  private IdentityTypeUtil() {
  }

  public static IdentityType fromGrpcIdentityType(final org.ghostprotocol.chat.common.IdentityType grpcIdentityType) {
    return switch (grpcIdentityType) {
      case IDENTITY_TYPE_ACI -> IdentityType.ACI;
      case IDENTITY_TYPE_PNI -> IdentityType.PNI;
      case IDENTITY_TYPE_UNSPECIFIED, UNRECOGNIZED -> throw Status.INVALID_ARGUMENT.asRuntimeException();
    };
  }

  public static org.ghostprotocol.chat.common.IdentityType toGrpcIdentityType(final IdentityType identityType) {
    return switch (identityType) {
      case ACI -> org.ghostprotocol.chat.common.IdentityType.IDENTITY_TYPE_ACI;
      case PNI -> org.ghostprotocol.chat.common.IdentityType.IDENTITY_TYPE_PNI;
    };
  }
}
