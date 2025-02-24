/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc.validators;

import static org.ghostprotocol.service.grpc.validators.ValidatorUtils.invalidArgument;

import com.google.protobuf.Descriptors;
import io.grpc.StatusException;
import java.util.Set;
import org.ghostprotocol.service.util.ImpossiblePhoneNumberException;
import org.ghostprotocol.service.util.NonNormalizedPhoneNumberException;
import org.ghostprotocol.service.util.Util;

public class E164FieldValidator extends BaseFieldValidator<Boolean> {

  public E164FieldValidator() {
    super("e164", Set.of(Descriptors.FieldDescriptor.Type.STRING), MissingOptionalAction.SUCCEED, false);
  }

  @Override
  protected Boolean resolveExtensionValue(final Object extensionValue) throws StatusException {
    return requireFlagExtension(extensionValue);
  }

  @Override
  protected void validateStringValue(
      final Boolean extensionValue,
      final String fieldValue) throws StatusException {
    try {
      Util.requireNormalizedNumber(fieldValue);
    } catch (final ImpossiblePhoneNumberException | NonNormalizedPhoneNumberException e) {
      throw invalidArgument("value is not in E164 format");
    }
  }
}
