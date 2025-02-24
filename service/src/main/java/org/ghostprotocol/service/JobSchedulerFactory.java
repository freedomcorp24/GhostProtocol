package org.ghostprotocol.textsecuregcm.workers;

import org.ghostprotocol.textsecuregcm.GhostProtocolServerConfiguration;
import org.ghostprotocol.textsecuregcm.scheduler.JobScheduler;

public interface JobSchedulerFactory {

  JobScheduler buildJobScheduler(CommandDependencies commandDependencies, GhostProtocolServerConfiguration configuration);
}
