/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.badges;

import java.util.List;
import java.util.Locale;
import org.ghostprotocol.service.entities.Badge;

public interface BadgeTranslator {
  Badge translate(List<Locale> acceptableLanguages, String badgeId);
}
