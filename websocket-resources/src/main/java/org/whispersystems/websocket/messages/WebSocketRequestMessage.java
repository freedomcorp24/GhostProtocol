/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.websocket.messages;

import java.util.Map;
import java.util.Optional;

public interface WebSocketRequestMessage {

  public String             getVerb();
  public String             getPath();
  public Map<String,String> getHeaders();
  public Optional<byte[]> getBody();
  public long               getRequestId();
  public boolean            hasRequestId();

}
