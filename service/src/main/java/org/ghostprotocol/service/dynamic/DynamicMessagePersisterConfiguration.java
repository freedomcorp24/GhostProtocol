/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.configuration.dynamic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DynamicMessagePersisterConfiguration {

  @JsonProperty
  private boolean persistenceEnabled = true;

  public boolean isPersistenceEnabled() {
    return persistenceEnabled;
  }
}
