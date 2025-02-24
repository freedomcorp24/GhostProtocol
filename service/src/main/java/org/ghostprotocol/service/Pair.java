/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.service.util;

import java.util.Map;

public record Pair<T1, T2>(T1 first, T2 second) {
  public Pair(org.ghostprotocol.protocol.util.Pair<T1, T2> p) {
    this(p.first(), p.second());
  }

  public Pair(Map.Entry<T1, T2> e) {
    this(e.getKey(), e.getValue());
  }
}
