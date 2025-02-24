/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.limits;

import static com.codahale.metrics.MetricRegistry.name;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.ghostprotocol.ghostprotocol.captcha.Action;
import org.ghostprotocol.ghostprotocol.captcha.CaptchaChecker;
import org.ghostprotocol.ghostprotocol.controllers.RateLimitExceededException;
import org.ghostprotocol.ghostprotocol.metrics.UserAgentTagUtil;
import org.ghostprotocol.ghostprotocol.push.NotPushRegisteredException;
import org.ghostprotocol.ghostprotocol.spam.ChallengeType;
import org.ghostprotocol.ghostprotocol.spam.RateLimitChallengeListener;
import org.ghostprotocol.ghostprotocol.storage.Account;
import org.ghostprotocol.ghostprotocol.util.Util;

public class RateLimitChallengeManager {

  private final PushChallengeManager pushChallengeManager;
  private final CaptchaChecker captchaChecker;
  private final RateLimiters rateLimiters;

  private final List<RateLimitChallengeListener> rateLimitChallengeListeners;

  private static final String CAPTCHA_ATTEMPT_COUNTER_NAME = name(RateLimitChallengeManager.class, "captcha",
      "attempt");
  private static final String RESET_RATE_LIMIT_EXCEEDED_COUNTER_NAME = name(RateLimitChallengeManager.class, "resetRateLimitExceeded");

  private static final String SOURCE_COUNTRY_TAG_NAME = "sourceCountry";
  private static final String SUCCESS_TAG_NAME = "success";

  public RateLimitChallengeManager(
      final PushChallengeManager pushChallengeManager,
      final CaptchaChecker captchaChecker,
      final RateLimiters rateLimiters,
      final List<RateLimitChallengeListener> rateLimitChallengeListeners) {

    this.pushChallengeManager = pushChallengeManager;
    this.captchaChecker = captchaChecker;
    this.rateLimiters = rateLimiters;
    this.rateLimitChallengeListeners = rateLimitChallengeListeners;
  }

  public void answerPushChallenge(final Account account, final String challenge) throws RateLimitExceededException {
    rateLimiters.getPushChallengeAttemptLimiter().validate(account.getUuid());

    final boolean challengeSuccess = pushChallengeManager.answerChallenge(account, challenge);

    if (challengeSuccess) {
      rateLimiters.getPushChallengeSuccessLimiter().validate(account.getUuid());
      resetRateLimits(account, ChallengeType.PUSH);
    }
  }

  public boolean answerCaptchaChallenge(final Account account, final String captcha, final String mostRecentProxyIp,
      final String userAgent, final Optional<Float> scoreThreshold)
      throws RateLimitExceededException, IOException {

    rateLimiters.getCaptchaChallengeAttemptLimiter().validate(account.getUuid());

    final boolean challengeSuccess = captchaChecker.verify(Optional.of(account.getUuid()), Action.CHALLENGE, captcha, mostRecentProxyIp, userAgent).isValid(scoreThreshold);

    final Tags tags = Tags.of(
        Tag.of(SOURCE_COUNTRY_TAG_NAME, Util.getCountryCode(account.getNumber())),
        Tag.of(SUCCESS_TAG_NAME, String.valueOf(challengeSuccess)),
        UserAgentTagUtil.getPlatformTag(userAgent)
    );

    Metrics.counter(CAPTCHA_ATTEMPT_COUNTER_NAME, tags).increment();

    if (challengeSuccess) {
      rateLimiters.getCaptchaChallengeSuccessLimiter().validate(account.getUuid());
      resetRateLimits(account, ChallengeType.CAPTCHA);
    }
    return challengeSuccess;
  }

  private void resetRateLimits(final Account account, final ChallengeType type) throws RateLimitExceededException {
    try {
      rateLimiters.getRateLimitResetLimiter().validate(account.getUuid());
    } catch (final RateLimitExceededException e) {
      Metrics.counter(RESET_RATE_LIMIT_EXCEEDED_COUNTER_NAME,
          SOURCE_COUNTRY_TAG_NAME, Util.getCountryCode(account.getNumber())).increment();

      throw e;
    }

    rateLimitChallengeListeners.forEach(listener -> listener.handleRateLimitChallengeAnswered(account, type));
  }

  public void sendPushChallenge(final Account account) throws NotPushRegisteredException {
    pushChallengeManager.sendChallenge(account);
  }
}
