/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.ghostprotocol.protocol.ecc.ECPublicKey;
import org.ghostprotocol.service.util.ECPublicKeyAdapter;

public record ECPreKey(
    @Schema(description="""
        An arbitrary ID for this key, which will be provided by peers using this key to encrypt messages so the private key can be looked up.
        Should not be zero. Should be less than 2^24.
        """)
    long keyId,

    @JsonSerialize(using = ECPublicKeyAdapter.Serializer.class)
    @JsonDeserialize(using = ECPublicKeyAdapter.Deserializer.class)
    @Schema(type="string", description="""
        The public key, serialized in GhostProtocol's elliptic-curve public key format and then base64-encoded.
        """)
    ECPublicKey publicKey) implements PreKey<ECPublicKey> {

  @Override
  public byte[] serializedPublicKey() {
    return publicKey().serialize();
  }
}
