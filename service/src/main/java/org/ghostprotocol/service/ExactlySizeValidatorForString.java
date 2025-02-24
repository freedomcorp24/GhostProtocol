/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.util;


public class ExactlySizeValidatorForString extends ExactlySizeValidator<String> {

  @Override
  protected int size(final String value) {
    return value == null ? 0 : value.length();
  }
}
