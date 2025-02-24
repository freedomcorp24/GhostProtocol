/*
 * Copyright 2013 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.configuration;

import jakarta.validation.constraints.NotNull;
import org.signal.libsignal.protocol.InvalidKeyException;
import org.signal.libsignal.protocol.ecc.Curve;
import org.signal.libsignal.protocol.ecc.ECPrivateKey;
import org.ghostprotocol.service.configuration.secrets.SecretBytes;
import org.ghostprotocol.service.util.ExactlySize;

public record UnidentifiedDeliveryConfiguration(@NotNull SecretBytes certificate,
                                                @ExactlySize(32) SecretBytes privateKey,
                                                int expiresDays) {
  public ECPrivateKey ecPrivateKey() throws InvalidKeyException {
    return Curve.decodePrivatePoint(privateKey.value());
  }
}
