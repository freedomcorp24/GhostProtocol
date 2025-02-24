/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.storage;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.ghostprotocol.service.registration.VerificationSession;

public class VerificationSessionManager {

  private final VerificationSessions verificationSessions;

  public VerificationSessionManager(final VerificationSessions verificationSessions) {
    this.verificationSessions = verificationSessions;
  }

  public CompletableFuture<Void> insert(final String encodedSessionId, final VerificationSession verificationSession) {
    return verificationSessions.insert(encodedSessionId, verificationSession);
  }

  public CompletableFuture<Void> update(final String encodedSessionId, final VerificationSession verificationSession) {
    return verificationSessions.update(encodedSessionId, verificationSession);
  }

  public CompletableFuture<Optional<VerificationSession>> findForId(final String encodedSessionId) {
    return verificationSessions.findForKey(encodedSessionId);
  }

}
