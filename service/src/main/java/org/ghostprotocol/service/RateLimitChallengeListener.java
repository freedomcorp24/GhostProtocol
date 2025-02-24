/*
 * Copyright 2013-2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.spam;


import org.ghostprotocol.service.storage.Account;
import java.io.IOException;

public interface RateLimitChallengeListener {

  void handleRateLimitChallengeAnswered(Account account, ChallengeType type);
}
