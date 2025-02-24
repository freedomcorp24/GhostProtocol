/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.annotation.Nullable;
import org.signal.libsignal.zkgroup.profiles.ExpiringProfileKeyCredentialResponse;

public class ExpiringProfileKeyCredentialProfileResponse extends CredentialProfileResponse {

  @JsonProperty
  @JsonSerialize(using = ExpiringProfileKeyCredentialResponseAdapter.Serializing.class)
  @JsonDeserialize(using = ExpiringProfileKeyCredentialResponseAdapter.Deserializing.class)
  @Nullable
  private ExpiringProfileKeyCredentialResponse credential;

  public ExpiringProfileKeyCredentialProfileResponse() {
  }

  public ExpiringProfileKeyCredentialProfileResponse(final VersionedProfileResponse versionedProfileResponse,
      @Nullable final ExpiringProfileKeyCredentialResponse credential) {

    super(versionedProfileResponse);
    this.credential = credential;
  }

  @Nullable
  public ExpiringProfileKeyCredentialResponse getCredential() {
    return credential;
  }
}
