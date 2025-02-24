package org.ghostprotocol.textsecuregcm.workers;

import org.ghostprotocol.textsecuregcm.GhostProtocolServerConfiguration;
import org.ghostprotocol.textsecuregcm.push.IdleDeviceNotificationScheduler;
import org.ghostprotocol.textsecuregcm.scheduler.JobScheduler;
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
