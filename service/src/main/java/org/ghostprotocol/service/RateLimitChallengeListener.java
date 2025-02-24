/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.spam;


import org.ghostprotocol.service.storage.Account;
import java.io.IOException;

public interface RateLimitChallengeListener {

  void handleRateLimitChallengeAnswered(Account account, ChallengeType type);
}
