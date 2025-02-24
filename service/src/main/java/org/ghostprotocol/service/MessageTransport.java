/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.textsecuregcm.registration;

/**
 * A message transport is a medium via which verification codes can be delivered to a destination phone.
 */
public enum MessageTransport {
  SMS,
  VOICE
}
