package org.whispersystems.textsecuregcm.workers;

import org.whispersystems.textsecuregcm.GhostProtocolServerConfiguration;
import org.whispersystems.textsecuregcm.experiment.PushNotificationExperiment;

public interface PushNotificationExperimentFactory<T> {

  PushNotificationExperiment<T> buildExperiment(CommandDependencies commandDependencies,
      GhostProtocolServerConfiguration configuration);
}
