/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration.dynamic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;

public class DynamicPaymentsConfiguration {

  @JsonProperty
  private List<String> disallowedPrefixes = Collections.emptyList();

  public List<String> getDisallowedPrefixes() {
    return disallowedPrefixes;
  }
}
