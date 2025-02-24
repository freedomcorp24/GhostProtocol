/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.util;

import org.signal.libsignal.usernames.BaseUsernameException;
import org.signal.libsignal.usernames.Username;

public class UsernameHashZkProofVerifier {
  public void verifyProof(final byte[] proof, final byte[] hash) throws BaseUsernameException {
    Username.verifyProof(proof, hash);
  }
}
