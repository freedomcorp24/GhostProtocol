/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.storage;

public class RefreshingAccountNotFoundException extends RuntimeException {

  public RefreshingAccountNotFoundException(final String message) {
    super(message);
  }

}
