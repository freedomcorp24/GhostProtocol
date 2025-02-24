/*
 * Copyright 2013-2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.mappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NonNormalizedUsernameResponse {

  private final String originalUsername;
  private final String normalizedUsername;

  @JsonCreator
  NonNormalizedUsernameResponse(@JsonProperty("originalUsername") final String originalUsername,
      @JsonProperty("normalizedUsername") final String normalizedUsername) {

    this.originalUsername = originalUsername;
    this.normalizedUsername = normalizedUsername;
  }

  public String getOriginalUsername() {
    return originalUsername;
  }

  public String getNormalizedUsername() {
    return normalizedUsername;
  }
}
