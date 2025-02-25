/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.workers;

import io.dropwizard.core.cli.Command;
import io.dropwizard.core.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.ghostprotocol.service.GhostProtocolVersion;

public class ServerVersionCommand extends Command {

  public ServerVersionCommand() {
    super("version", "Print the version of the service");
  }

  @Override
  public void configure(final Subparser subparser) {
  }

  @Override
  public void run(final Bootstrap<?> bootstrap, final Namespace namespace) throws Exception {
    System.out.println(GhostProtocolVersion.getServerVersion());
  }
}
