package org.whispersystems.textsecuregcm.workers;

import org.whispersystems.textsecuregcm.GhostProtocolServerConfiguration;
import org.whispersystems.textsecuregcm.scheduler.JobScheduler;

public interface JobSchedulerFactory {

  JobScheduler buildJobScheduler(CommandDependencies commandDependencies, GhostProtocolServerConfiguration configuration);
}
