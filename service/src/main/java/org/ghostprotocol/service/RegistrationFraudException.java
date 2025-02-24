/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.textsecuregcm.registration;

public class RegistrationFraudException extends Exception {
  public RegistrationFraudException(final RegistrationServiceSenderException cause) {
    super(null, cause, true, false);
  }
}
