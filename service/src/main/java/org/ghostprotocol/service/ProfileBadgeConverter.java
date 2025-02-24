/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.badges;

import java.util.List;
import java.util.Locale;
import org.ghostprotocol.service.entities.Badge;
import org.ghostprotocol.service.storage.AccountBadge;

public interface ProfileBadgeConverter {

  /**
   * Converts the {@link AccountBadge}s for an account into the objects
   * that can be returned on a profile fetch.
   */
  List<Badge> convert(List<Locale> acceptableLanguages, List<AccountBadge> accountBadges, boolean isSelf);
}
