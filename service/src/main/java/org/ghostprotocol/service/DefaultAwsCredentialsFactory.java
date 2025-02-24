/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import com.fasterxml.jackson.annotation.JsonTypeName;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;

@JsonTypeName("default")
public record DefaultAwsCredentialsFactory() implements AwsCredentialsProviderFactory {

  public AwsCredentialsProvider build() {
    return WebIdentityTokenFileCredentialsProvider.create();
  }
}
