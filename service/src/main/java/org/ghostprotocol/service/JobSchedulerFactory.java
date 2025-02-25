package org.ghostprotocol.service.workers;

import org.ghostprotocol.service.GhostProtocolServerConfiguration;
import org.ghostprotocol.service.scheduler.JobScheduler;

public interface JobSchedulerFactory {

  JobScheduler buildJobScheduler(CommandDependencies commandDependencies, GhostProtocolServerConfiguration configuration);
}
