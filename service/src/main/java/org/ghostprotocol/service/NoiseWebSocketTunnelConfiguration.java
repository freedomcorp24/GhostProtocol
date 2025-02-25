package org.ghostprotocol.ghostprotocol.configuration;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import javax.annotation.Nullable;
import org.ghostprotocol.protocol.InvalidKeyException;
import org.ghostprotocol.protocol.ecc.Curve;
import org.ghostprotocol.protocol.ecc.ECKeyPair;
import org.ghostprotocol.protocol.ecc.ECPrivateKey;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretBytes;
import org.ghostprotocol.ghostprotocol.configuration.secrets.SecretString;

public record NoiseWebSocketTunnelConfiguration(@Positive int port,
                                                @Nullable String tlsKeyStoreFile,
                                                @Nullable String tlsKeyStoreEntryAlias,
                                                @Nullable SecretString tlsKeyStorePassword,
                                                @NotNull SecretBytes noiseStaticPrivateKey,
                                                @NotNull SecretString recognizedProxySecret) {

  public ECKeyPair noiseStaticKeyPair() throws InvalidKeyException {
    final ECPrivateKey privateKey = Curve.decodePrivatePoint(noiseStaticPrivateKey().value());

    return new ECKeyPair(privateKey.publicKey(), privateKey);
  }
}
