/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryCertificate {

  private final byte[] certificate;

  @JsonCreator
  public DeliveryCertificate(
      @JsonProperty("certificate") byte[] certificate) {
    this.certificate = certificate;
  }

  public byte[] getCertificate() {
    return certificate;
  }
}
