/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageResponse {

  @JsonProperty
  private boolean needsSync;

  public SendMessageResponse() {}

  public SendMessageResponse(boolean needsSync) {
    this.needsSync = needsSync;
  }
}
