/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.workers;

import org.ghostprotocol.service.GhostProtocolServerConfiguration;
import org.ghostprotocol.service.configuration.DynamoDbTables;
import org.ghostprotocol.service.experiment.DeviceLastSeenState;
import org.ghostprotocol.service.experiment.PushNotificationExperiment;
import org.ghostprotocol.service.experiment.ZeroTtlPushNotificationExperiment;
import org.ghostprotocol.service.push.ZeroTtlNotificationScheduler;
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
