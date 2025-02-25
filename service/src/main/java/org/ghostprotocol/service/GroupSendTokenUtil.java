/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import com.google.protobuf.ByteString;
import io.grpc.Status;

import java.time.Clock;
import java.util.List;
import org.ghostprotocol.protocol.ServiceId;
import org.ghostprotocol.protocol.zkgroup.InvalidInputException;
import org.ghostprotocol.protocol.zkgroup.ServerSecretParams;
import org.ghostprotocol.protocol.zkgroup.VerificationFailedException;
import org.ghostprotocol.protocol.zkgroup.groupsend.GroupSendDerivedKeyPair;
import org.ghostprotocol.protocol.zkgroup.groupsend.GroupSendFullToken;
import org.ghostprotocol.service.identity.ServiceIdentifier;

import reactor.core.publisher.Mono;

public class GroupSendTokenUtil {

  private final ServerSecretParams serverSecretParams;
  private final Clock clock;

  public GroupSendTokenUtil(final ServerSecretParams serverSecretParams, final Clock clock) {
    this.serverSecretParams = serverSecretParams;
    this.clock = clock;
  }

  public Mono<Void> checkGroupSendToken(final ByteString serializedGroupSendToken, List<ServiceIdentifier> serviceIdentifiers) {
    try {
      final GroupSendFullToken token = new GroupSendFullToken(serializedGroupSendToken.toByteArray());
      final List<ServiceId> serviceIds = serviceIdentifiers.stream().map(ServiceIdentifier::toLibsignal).toList();
      token.verify(serviceIds, clock.instant(), GroupSendDerivedKeyPair.forExpiration(token.getExpiration(), serverSecretParams));
      return Mono.empty();
    } catch (InvalidInputException e) {
      return Mono.error(Status.INVALID_ARGUMENT.asException());
    } catch (VerificationFailedException e) {
      return Mono.error(Status.UNAUTHENTICATED.asException());
    }
  }
}
