/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc.validators;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.StatusException;

public interface FieldValidator {

  void validate(Object extensionValue, Descriptors.FieldDescriptor fd, GeneratedMessageV3 msg)
      throws StatusException;
}
