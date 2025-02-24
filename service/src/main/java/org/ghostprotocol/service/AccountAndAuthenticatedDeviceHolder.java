/*
 * Copyright 2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.auth;

import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

public interface AccountAndAuthenticatedDeviceHolder {

  Account getAccount();

  Device getAuthenticatedDevice();
}
