/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.entities.RegistrationServiceSession;
import javax.annotation.Nullable;
import java.time.Duration;

public class VerificationSessionRateLimitExceededException extends RateLimitExceededException {

  private final RegistrationServiceSession registrationServiceSession;

  /**
   * Constructs a new exception indicating when it may become safe to retry
   *
   * @param registrationServiceSession the associated registration session
   * @param retryDuration              A duration to wait before retrying, null if no duration can be indicated
   * @param legacy                     whether to use a legacy status code when mapping the exception to an HTTP
   *                                   response
   */
  public VerificationSessionRateLimitExceededException(
      final RegistrationServiceSession registrationServiceSession, @Nullable final Duration retryDuration,
      final boolean legacy) {
    super(retryDuration);
    this.registrationServiceSession = registrationServiceSession;
  }

  public RegistrationServiceSession getRegistrationSession() {
    return registrationServiceSession;
  }
}
