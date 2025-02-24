/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import com.fasterxml.jackson.databind.util.StdConverter;
import java.net.URL;

final class URLSerializationConverter extends StdConverter<URL, String> {

  @Override
  public String convert(final URL value) {
    return value.toString();
  }
}
