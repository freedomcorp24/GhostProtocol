package org.ghostprotocol.service.workers;

import org.ghostprotocol.service.GhostProtocolServerConfiguration;
import org.ghostprotocol.service.experiment.PushNotificationExperiment;

public interface PushNotificationExperimentFactory<T> {

  PushNotificationExperiment<T> buildExperiment(CommandDependencies commandDependencies,
      GhostProtocolServerConfiguration configuration);
}
