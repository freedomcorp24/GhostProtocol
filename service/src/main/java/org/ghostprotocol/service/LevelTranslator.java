/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.textsecuregcm.badges;

import java.util.List;
import java.util.Locale;

public interface LevelTranslator {
  String translate(List<Locale> acceptableLanguages, String badgeId);
}
