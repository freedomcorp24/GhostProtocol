package org.ghostprotocol.service.workers;

import org.ghostprotocol.service.GhostProtocolServerConfiguration;
import org.ghostprotocol.service.push.IdleDeviceNotificationScheduler;
import org.ghostprotocol.service.scheduler.JobScheduler;
import java.time.Clock;

public class IdleDeviceNotificationSchedulerFactory implements JobSchedulerFactory {

  @Override
  public JobScheduler buildJobScheduler(final CommandDependencies commandDependencies,
      final GhostProtocolServerConfiguration configuration) {

    return new IdleDeviceNotificationScheduler(commandDependencies.accountsManager(),
        commandDependencies.pushNotificationManager(),
        commandDependencies.dynamoDbAsyncClient(),
        configuration.getDynamoDbTables().getScheduledJobs().getTableName(),
        configuration.getDynamoDbTables().getScheduledJobs().getExpiration(),
        Clock.systemUTC());
  }
}
