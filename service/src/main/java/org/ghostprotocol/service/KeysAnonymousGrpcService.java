/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import com.google.protobuf.ByteString;
import io.grpc.Status;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.Arrays;
import java.util.List;

import org.ghostprotocol.chat.keys.CheckIdentityKeyRequest;
import org.ghostprotocol.chat.keys.CheckIdentityKeyResponse;
import org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest;
import org.ghostprotocol.chat.keys.GetPreKeysResponse;
import org.ghostprotocol.chat.keys.ReactorKeysAnonymousGrpc;
import org.ghostprotocol.protocol.IdentityKey;
import org.ghostprotocol.protocol.zkgroup.ServerSecretParams;
import org.ghostprotocol.service.auth.UnidentifiedAccessUtil;
import org.ghostprotocol.service.identity.ServiceIdentifier;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.AccountsManager;
import org.ghostprotocol.service.storage.KeysManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

public class KeysAnonymousGrpcService extends ReactorKeysAnonymousGrpc.KeysAnonymousImplBase {

  private final AccountsManager accountsManager;
  private final KeysManager keysManager;
  private final GroupSendTokenUtil groupSendTokenUtil;

  public KeysAnonymousGrpcService(
      final AccountsManager accountsManager, final KeysManager keysManager, final ServerSecretParams serverSecretParams, final Clock clock) {
    this.accountsManager = accountsManager;
    this.keysManager = keysManager;
    this.groupSendTokenUtil = new GroupSendTokenUtil(serverSecretParams, clock);
}

  @Override
  public Mono<GetPreKeysResponse> getPreKeys(final GetPreKeysAnonymousRequest request) {
    final ServiceIdentifier serviceIdentifier =
        ServiceIdentifierUtil.fromGrpcServiceIdentifier(request.getRequest().getTargetIdentifier());

    final byte deviceId = request.getRequest().hasDeviceId()
        ? DeviceIdUtil.validate(request.getRequest().getDeviceId())
        : KeysGrpcHelper.ALL_DEVICES;

    return switch (request.getAuthorizationCase()) {
      case GROUP_SEND_TOKEN ->
          groupSendTokenUtil.checkGroupSendToken(request.getGroupSendToken(), List.of(serviceIdentifier))
              .then(lookUpAccount(serviceIdentifier, Status.NOT_FOUND))
              .flatMap(targetAccount -> KeysGrpcHelper.getPreKeys(targetAccount, serviceIdentifier.identityType(), deviceId, keysManager));
      case UNIDENTIFIED_ACCESS_KEY ->
          lookUpAccount(serviceIdentifier, Status.UNAUTHENTICATED)
              .flatMap(targetAccount ->
                  UnidentifiedAccessUtil.checkUnidentifiedAccess(targetAccount, request.getUnidentifiedAccessKey().toByteArray())
                  ? KeysGrpcHelper.getPreKeys(targetAccount, serviceIdentifier.identityType(), deviceId, keysManager)
                  : Mono.error(Status.UNAUTHENTICATED.asException()));
      default -> Mono.error(Status.INVALID_ARGUMENT.asException());
    };
  }

  @Override
  public Flux<CheckIdentityKeyResponse> checkIdentityKeys(final Flux<CheckIdentityKeyRequest> requests) {
    return requests
        .map(request -> Tuples.of(ServiceIdentifierUtil.fromGrpcServiceIdentifier(request.getTargetIdentifier()),
            request.getFingerprint().toByteArray()))
        .flatMap(serviceIdentifierAndFingerprint -> Mono.fromFuture(
                () -> accountsManager.getByServiceIdentifierAsync(serviceIdentifierAndFingerprint.getT1()))
            .flatMap(Mono::justOrEmpty)
            .filter(account -> !fingerprintMatches(account.getIdentityKey(serviceIdentifierAndFingerprint.getT1()
                .identityType()), serviceIdentifierAndFingerprint.getT2()))
            .map(account -> CheckIdentityKeyResponse.newBuilder()
                    .setTargetIdentifier(
                        ServiceIdentifierUtil.toGrpcServiceIdentifier(serviceIdentifierAndFingerprint.getT1()))
                    .setIdentityKey(ByteString.copyFrom(account.getIdentityKey(serviceIdentifierAndFingerprint.getT1()
                        .identityType()).serialize()))
                    .build())
        );
  }

  private Mono<Account> lookUpAccount(final ServiceIdentifier serviceIdentifier, final Status onNotFound) {
    return Mono.fromFuture(() -> accountsManager.getByServiceIdentifierAsync(serviceIdentifier))
      .flatMap(Mono::justOrEmpty)
      .switchIfEmpty(Mono.error(onNotFound.asException()));
  }

  private static boolean fingerprintMatches(final IdentityKey identityKey, final byte[] fingerprint) {
    final byte[] digest;
    try {
      digest = MessageDigest.getInstance("SHA-256").digest(identityKey.serialize());
    } catch (NoSuchAlgorithmException e) {
      // SHA-256 should always be supported as an algorithm
      throw new AssertionError("All Java implementations must support the SHA-256 message digest");
    }

    return Arrays.equals(digest, 0, 4, fingerprint, 0, 4);
  }
}
