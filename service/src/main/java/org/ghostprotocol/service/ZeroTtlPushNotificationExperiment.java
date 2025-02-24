/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.experiment;

import org.ghostprotocol.service.push.ZeroTtlNotificationScheduler;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;
import org.ghostprotocol.service.workers.IdleWakeupEligibilityChecker;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

public class ZeroTtlPushNotificationExperiment extends IdleDevicePushNotificationExperiment {
  private static final LocalTime PREFERRED_NOTIFICATION_TIME = LocalTime.of(14, 0);

  private final ZeroTtlNotificationScheduler zeroTtlNotificationScheduler;

  public ZeroTtlPushNotificationExperiment(
      final IdleWakeupEligibilityChecker idleWakeupEligibilityChecker,
      final ZeroTtlNotificationScheduler zeroTtlNotificationScheduler) {
    super(idleWakeupEligibilityChecker);
    this.zeroTtlNotificationScheduler = zeroTtlNotificationScheduler;
  }

  @Override
  boolean isIdleDeviceEligible(final Account account, final Device idleDevice, final DeviceLastSeenState state) {
    return state.pushTokenType() == DeviceLastSeenState.PushTokenType.FCM;
  }

  @Override
  public String getExperimentName() {
    return "zero-ttl-notification";
  }

  @Override
  public Class<DeviceLastSeenState> getStateClass() {
    return DeviceLastSeenState.class;
  }

  @Override
  public CompletableFuture<Void> applyExperimentTreatment(final Account account, final Device device) {
    return zeroTtlNotificationScheduler.scheduleNotification(account, device, PREFERRED_NOTIFICATION_TIME, true);
  }

  @Override
  public CompletableFuture<Void> applyControlTreatment(final Account account, final Device device) {
    return zeroTtlNotificationScheduler.scheduleNotification(account, device, PREFERRED_NOTIFICATION_TIME, false);
  }
}
