/*
 * Copyright 2025 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.textsecuregcm.workers;

import org.ghostprotocol.textsecuregcm.GhostProtocolServerConfiguration;
import org.ghostprotocol.textsecuregcm.configuration.DynamoDbTables;
import org.ghostprotocol.textsecuregcm.experiment.DeviceLastSeenState;
import org.ghostprotocol.textsecuregcm.experiment.PushNotificationExperiment;
import org.ghostprotocol.textsecuregcm.experiment.ZeroTtlPushNotificationExperiment;
import org.ghostprotocol.textsecuregcm.push.ZeroTtlNotificationScheduler;
import java.time.Clock;

public class ZeroTtlPushNotificationExperimentFactory implements PushNotificationExperimentFactory<DeviceLastSeenState> {

  @Override
  public PushNotificationExperiment<DeviceLastSeenState> buildExperiment(final CommandDependencies commandDependencies,
      final GhostProtocolServerConfiguration configuration) {

    final DynamoDbTables.TableWithExpiration tableConfiguration = configuration.getDynamoDbTables().getScheduledJobs();

    final Clock clock = Clock.systemUTC();

    return new ZeroTtlPushNotificationExperiment(
        new IdleWakeupEligibilityChecker(clock, commandDependencies.messagesManager()),
        new ZeroTtlNotificationScheduler(
        commandDependencies.accountsManager(),
        commandDependencies.pushNotificationManager(),
        commandDependencies.dynamoDbAsyncClient(),
        tableConfiguration.getTableName(),
        tableConfiguration.getExpiration(),
        clock));
  }
}
