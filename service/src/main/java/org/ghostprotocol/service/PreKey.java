/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

public interface PreKey<K> {

  long keyId();

  K publicKey();

  byte[] serializedPublicKey();
}
