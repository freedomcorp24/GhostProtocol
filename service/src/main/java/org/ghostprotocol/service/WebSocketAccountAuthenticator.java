/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.websocket;

import static org.ghostprotocol.ghostprotocol.util.HeaderUtils.basicCredentialsFromAuthHeader;

import com.google.common.net.HttpHeaders;
import javax.annotation.Nullable;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.ghostprotocol.ghostprotocol.auth.AccountAuthenticator;
import org.ghostprotocol.ghostprotocol.auth.AuthenticatedDevice;
import org.ghostprotocol.websocket.ReusableAuth;
import org.ghostprotocol.websocket.auth.AuthenticationException;
import org.ghostprotocol.websocket.auth.PrincipalSupplier;
import org.ghostprotocol.websocket.auth.WebSocketAuthenticator;


public class WebSocketAccountAuthenticator implements WebSocketAuthenticator<AuthenticatedDevice> {

  private static final ReusableAuth<AuthenticatedDevice> CREDENTIALS_NOT_PRESENTED = ReusableAuth.anonymous();

  private static final ReusableAuth<AuthenticatedDevice> INVALID_CREDENTIALS_PRESENTED = ReusableAuth.invalid();
  private final AccountAuthenticator accountAuthenticator;
  private final PrincipalSupplier<AuthenticatedDevice> principalSupplier;

  public WebSocketAccountAuthenticator(final AccountAuthenticator accountAuthenticator,
      final PrincipalSupplier<AuthenticatedDevice> principalSupplier) {
    this.accountAuthenticator = accountAuthenticator;
    this.principalSupplier = principalSupplier;
  }

  @Override
  public ReusableAuth<AuthenticatedDevice> authenticate(final UpgradeRequest request)
      throws AuthenticationException {
    try {
      return authenticatedAccountFromHeaderAuth(request.getHeader(HttpHeaders.AUTHORIZATION));
    } catch (final Exception e) {
      // this will be handled and logged upstream
      // the most likely exception is a transient error connecting to account storage
      throw new AuthenticationException(e);
    }
  }

  private ReusableAuth<AuthenticatedDevice> authenticatedAccountFromHeaderAuth(@Nullable final String authHeader) {
    if (authHeader == null) {
      return CREDENTIALS_NOT_PRESENTED;
    }
    return basicCredentialsFromAuthHeader(authHeader)
        .flatMap(credentials -> accountAuthenticator.authenticate(credentials))
        .map(authenticatedAccount -> ReusableAuth.authenticated(authenticatedAccount, this.principalSupplier))
        .orElse(INVALID_CREDENTIALS_PRESENTED);
  }
}
