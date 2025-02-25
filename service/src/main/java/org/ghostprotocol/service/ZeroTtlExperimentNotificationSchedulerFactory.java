package org.ghostprotocol.service.workers;

import java.time.Clock;
import org.ghostprotocol.service.GhostProtocolServerConfiguration;
import org.ghostprotocol.service.configuration.DynamoDbTables;
import org.ghostprotocol.service.push.ZeroTtlNotificationScheduler;
import org.ghostprotocol.service.scheduler.JobScheduler;

public class ZeroTtlExperimentNotificationSchedulerFactory implements JobSchedulerFactory {

  @Override
  public JobScheduler buildJobScheduler(final CommandDependencies commandDependencies,
      final GhostProtocolServerConfiguration configuration) {
    final DynamoDbTables.TableWithExpiration tableConfiguration = configuration.getDynamoDbTables().getScheduledJobs();

    final Clock clock = Clock.systemUTC();

    return new ZeroTtlNotificationScheduler(
        commandDependencies.accountsManager(),
        commandDependencies.pushNotificationManager(),
        commandDependencies.dynamoDbAsyncClient(),
        tableConfiguration.getTableName(),
        tableConfiguration.getExpiration(),
        clock);
  }
}
