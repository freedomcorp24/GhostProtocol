/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.auth;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.security.InvalidKeyException;
import java.util.concurrent.TimeUnit;
import org.ghostprotocol.protocol.ecc.Curve;
import org.ghostprotocol.protocol.ecc.ECPrivateKey;
import org.ghostprotocol.service.entities.MessageProtos.SenderCertificate;
import org.ghostprotocol.service.entities.MessageProtos.ServerCertificate;
import org.ghostprotocol.service.identity.IdentityType;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

public class CertificateGenerator {

  private final ECPrivateKey      privateKey;
  private final int               expiresDays;
  private final ServerCertificate serverCertificate;

  public CertificateGenerator(byte[] serverCertificate, ECPrivateKey privateKey, int expiresDays)
      throws InvalidProtocolBufferException
  {
    this.privateKey        = privateKey;
    this.expiresDays       = expiresDays;
    this.serverCertificate = ServerCertificate.parseFrom(serverCertificate);
  }

  public byte[] createFor(Account account, Device device, boolean includeE164) throws InvalidKeyException {
    SenderCertificate.Certificate.Builder builder = SenderCertificate.Certificate.newBuilder()
        .setSenderDevice(Math.toIntExact(device.getId()))
        .setExpires(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(expiresDays))
        .setIdentityKey(ByteString.copyFrom(account.getIdentityKey(IdentityType.ACI).serialize()))
        .setSigner(serverCertificate)
        .setSenderUuid(account.getUuid().toString());

    if (includeE164) {
      builder.setSender(account.getNumber());
    }

    byte[] certificate = builder.build().toByteArray();
    byte[] signature;
    try {
      signature = Curve.calculateSignature(privateKey, certificate);
    } catch (org.ghostprotocol.protocol.InvalidKeyException e) {
      throw new InvalidKeyException(e);
    }

    return SenderCertificate.newBuilder()
                            .setCertificate(ByteString.copyFrom(certificate))
                            .setSignature(ByteString.copyFrom(signature))
                            .build()
                            .toByteArray();
  }

}
