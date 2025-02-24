package org.ghostprotocol.textsecuregcm.workers;

import java.time.Clock;
import org.ghostprotocol.textsecuregcm.GhostProtocolServerConfiguration;
import org.ghostprotocol.textsecuregcm.configuration.DynamoDbTables;
import org.ghostprotocol.textsecuregcm.push.ZeroTtlNotificationScheduler;
import org.ghostprotocol.textsecuregcm.scheduler.JobScheduler;

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
