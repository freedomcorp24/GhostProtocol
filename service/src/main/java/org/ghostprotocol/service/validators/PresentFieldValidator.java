/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc.validators;

import static org.ghostprotocol.service.grpc.validators.ValidatorUtils.invalidArgument;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import io.grpc.StatusException;
import java.util.Set;

public class PresentFieldValidator extends BaseFieldValidator<Boolean> {

  public PresentFieldValidator() {
    super("present",
        Set.of(Descriptors.FieldDescriptor.Type.MESSAGE),
        MissingOptionalAction.FAIL,
        true);
  }

  @Override
  protected Boolean resolveExtensionValue(final Object extensionValue) throws StatusException {
    return requireFlagExtension(extensionValue);
  }

  @Override
  protected void validateMessageValue(final Boolean extensionValue, final Message msg) throws StatusException {
    if (msg == null) {
      throw invalidArgument("message expected to be present");
    }
  }
}
