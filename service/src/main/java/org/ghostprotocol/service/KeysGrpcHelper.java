/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.chat.grpc;

import com.google.protobuf.ByteString;
import io.grpc.Status;
import org.ghostprotocol.chat.common.EcPreKey;
import org.ghostprotocol.chat.common.EcSignedPreKey;
import org.ghostprotocol.chat.common.KemSignedPreKey;
import org.ghostprotocol.chat.keys.GetPreKeysResponse;
import org.ghostprotocol.chat.entities.ECPreKey;
import org.ghostprotocol.chat.entities.ECSignedPreKey;
import org.ghostprotocol.chat.entities.KEMSignedPreKey;
import org.ghostprotocol.chat.identity.IdentityType;
import org.ghostprotocol.chat.storage.Account;
import org.ghostprotocol.chat.storage.Device;
import org.ghostprotocol.chat.storage.KeysManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

class KeysGrpcHelper {

  static final byte ALL_DEVICES = 0;

  static Mono<GetPreKeysResponse> getPreKeys(final Account targetAccount,
      final IdentityType identityType,
      final byte targetDeviceId,
      final KeysManager keysManager) {

    final Flux<Device> devices = targetDeviceId == ALL_DEVICES
        ? Flux.fromIterable(targetAccount.getDevices())
        : Flux.from(Mono.justOrEmpty(targetAccount.getDevice(targetDeviceId)));

    return devices
        .switchIfEmpty(Mono.error(Status.NOT_FOUND.asException()))
        .flatMap(device -> Flux.merge(
                Mono.fromFuture(() -> keysManager.takeEC(targetAccount.getIdentifier(identityType), device.getId())),
                Mono.fromFuture(() -> keysManager.getEcSignedPreKey(targetAccount.getIdentifier(identityType), device.getId())),
                Mono.fromFuture(() -> keysManager.takePQ(targetAccount.getIdentifier(identityType), device.getId())))
            .flatMap(Mono::justOrEmpty)
            .reduce(GetPreKeysResponse.PreKeyBundle.newBuilder(), (builder, preKey) -> {
              if (preKey instanceof ECPreKey ecPreKey) {
                builder.setEcOneTimePreKey(EcPreKey.newBuilder()
                    .setKeyId(ecPreKey.keyId())
                    .setPublicKey(ByteString.copyFrom(ecPreKey.serializedPublicKey()))
                    .build());
              } else if (preKey instanceof ECSignedPreKey ecSignedPreKey) {
                builder.setEcSignedPreKey(EcSignedPreKey.newBuilder()
                    .setKeyId(ecSignedPreKey.keyId())
                    .setPublicKey(ByteString.copyFrom(ecSignedPreKey.serializedPublicKey()))
                    .setSignature(ByteString.copyFrom(ecSignedPreKey.signature()))
                    .build());
              } else if (preKey instanceof KEMSignedPreKey kemSignedPreKey) {
                builder.setKemOneTimePreKey(KemSignedPreKey.newBuilder()
                    .setKeyId(kemSignedPreKey.keyId())
                    .setPublicKey(ByteString.copyFrom(kemSignedPreKey.serializedPublicKey()))
                    .setSignature(ByteString.copyFrom(kemSignedPreKey.signature()))
                    .build());
              } else {
                throw new AssertionError("Unexpected pre-key type: " + preKey.getClass());
              }

              return builder;
            })
            // Cast device IDs to `int` to match data types in the response object’s protobuf definition
            .map(builder -> Tuples.of((int) device.getId(), builder.build())))
        .collectMap(Tuple2::getT1, Tuple2::getT2)
        .map(preKeyBundles -> GetPreKeysResponse.newBuilder()
            .setIdentityKey(ByteString.copyFrom(targetAccount.getIdentityKey(identityType).serialize()))
            .putAllPreKeys(preKeyBundles)
            .build());
  }
}
