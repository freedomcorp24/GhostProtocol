/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 *
 */

package org.ghostprotocol.service.util;

import org.ghostprotocol.service.storage.Device;

public class RegistrationIdValidator {
  public static boolean validRegistrationId(int registrationId) {
    return registrationId > 0 && registrationId <= Device.MAX_REGISTRATION_ID;
  }
}
