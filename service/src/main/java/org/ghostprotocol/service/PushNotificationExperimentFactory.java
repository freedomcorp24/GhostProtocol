package org.ghostprotocol.textsecuregcm.workers;

import org.ghostprotocol.textsecuregcm.GhostProtocolServerConfiguration;
import org.ghostprotocol.textsecuregcm.experiment.PushNotificationExperiment;

public interface PushNotificationExperimentFactory<T> {

  PushNotificationExperiment<T> buildExperiment(CommandDependencies commandDependencies,
      GhostProtocolServerConfiguration configuration);
}
