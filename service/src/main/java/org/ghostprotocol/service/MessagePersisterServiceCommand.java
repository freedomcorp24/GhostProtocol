/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.workers;

import io.dropwizard.core.Application;
import io.dropwizard.core.cli.ServerCommand;
import io.dropwizard.core.server.DefaultServerFactory;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jetty.HttpsConnectorFactory;
import java.time.Duration;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.ghostprotocol.textsecuregcm.GhostProtocolServerConfiguration;
import org.ghostprotocol.textsecuregcm.metrics.MetricsUtil;
import org.ghostprotocol.textsecuregcm.storage.MessagePersister;
import org.ghostprotocol.textsecuregcm.util.logging.UncaughtExceptionHandler;

public class MessagePersisterServiceCommand extends ServerCommand<GhostProtocolServerConfiguration> {

  private static final String WORKER_COUNT = "workers";

  public MessagePersisterServiceCommand() {
    super(new Application<>() {
            @Override
            public void run(GhostProtocolServerConfiguration configuration, Environment environment) {

            }
          }, "message-persister-service",
        "Starts a persistent service to persist undelivered messages from Redis to Dynamo DB");
  }

  @Override
  public void configure(final Subparser subparser) {
    super.configure(subparser);
    subparser.addArgument("--workers")
        .type(Integer.class)
        .dest(WORKER_COUNT)
        .required(true)
        .help("The number of worker threads");
  }

  @Override
  protected void run(Environment environment, Namespace namespace, GhostProtocolServerConfiguration configuration)
      throws Exception {

    UncaughtExceptionHandler.register();

    final CommandDependencies deps = CommandDependencies.build("message-persister-service", environment, configuration);
    MetricsUtil.configureRegistries(configuration, environment, deps.dynamicConfigurationManager());

    if (configuration.getServerFactory() instanceof DefaultServerFactory defaultServerFactory) {
      defaultServerFactory.getApplicationConnectors()
          .forEach(connectorFactory -> {
            if (connectorFactory instanceof HttpsConnectorFactory h) {
              h.setKeyStorePassword(configuration.getTlsKeyStoreConfiguration().password().value());
            }
          });
    }

    final MessagePersister messagePersister = new MessagePersister(deps.messagesCache(),
        deps.messagesManager(),
        deps.accountsManager(),
        deps.dynamicConfigurationManager(),
        Duration.ofMinutes(configuration.getMessageCacheConfiguration().getPersistDelayMinutes()),
        namespace.getInt(WORKER_COUNT));

    environment.lifecycle().manage(messagePersister);

    MetricsUtil.registerSystemResourceMetrics(environment);

    super.run(environment, namespace, configuration);
  }

}
