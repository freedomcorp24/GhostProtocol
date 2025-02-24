/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.util;

public class ExactlySizeValidatorForArraysOfByte extends ExactlySizeValidator<byte[]> {

  @Override
  protected int size(final byte[] value) {
    return value == null ? 0 : value.length;
  }
}
