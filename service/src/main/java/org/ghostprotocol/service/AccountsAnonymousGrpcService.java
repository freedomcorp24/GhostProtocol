/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import com.google.protobuf.ByteString;
import io.grpc.Status;
import org.ghostprotocol.chat.account.CheckAccountExistenceRequest;
import org.ghostprotocol.chat.account.CheckAccountExistenceResponse;
import org.ghostprotocol.chat.account.LookupUsernameHashRequest;
import org.ghostprotocol.chat.account.LookupUsernameHashResponse;
import org.ghostprotocol.chat.account.LookupUsernameLinkRequest;
import org.ghostprotocol.chat.account.LookupUsernameLinkResponse;
import org.ghostprotocol.chat.account.ReactorAccountsAnonymousGrpc;
import org.ghostprotocol.service.controllers.AccountController;
import org.ghostprotocol.service.identity.AciServiceIdentifier;
import org.ghostprotocol.service.identity.ServiceIdentifier;
import org.ghostprotocol.service.limits.RateLimiters;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.AccountsManager;
import org.ghostprotocol.service.util.UUIDUtil;
import reactor.core.publisher.Mono;
import java.util.Optional;
import java.util.UUID;

public class AccountsAnonymousGrpcService extends ReactorAccountsAnonymousGrpc.AccountsAnonymousImplBase {

  private final AccountsManager accountsManager;
  private final RateLimiters rateLimiters;

  public AccountsAnonymousGrpcService(final AccountsManager accountsManager, final RateLimiters rateLimiters) {
    this.accountsManager = accountsManager;
    this.rateLimiters = rateLimiters;
  }

  @Override
  public Mono<CheckAccountExistenceResponse> checkAccountExistence(final CheckAccountExistenceRequest request) {
    final ServiceIdentifier serviceIdentifier =
        ServiceIdentifierUtil.fromGrpcServiceIdentifier(request.getServiceIdentifier());

    return RateLimitUtil.rateLimitByRemoteAddress(rateLimiters.getCheckAccountExistenceLimiter())
        .then(Mono.fromFuture(() -> accountsManager.getByServiceIdentifierAsync(serviceIdentifier)))
        .map(Optional::isPresent)
        .map(accountExists -> CheckAccountExistenceResponse.newBuilder()
            .setAccountExists(accountExists)
            .build());
  }

  @Override
  public Mono<LookupUsernameHashResponse> lookupUsernameHash(final LookupUsernameHashRequest request) {
    if (request.getUsernameHash().size() != AccountController.USERNAME_HASH_LENGTH) {
      throw Status.INVALID_ARGUMENT
          .withDescription(String.format("Illegal username hash length; expected %d bytes, but got %d bytes",
              AccountController.USERNAME_HASH_LENGTH, request.getUsernameHash().size()))
          .asRuntimeException();
    }

    return RateLimitUtil.rateLimitByRemoteAddress(rateLimiters.getUsernameLookupLimiter())
        .then(Mono.fromFuture(() -> accountsManager.getByUsernameHash(request.getUsernameHash().toByteArray())))
        .map(maybeAccount -> maybeAccount.orElseThrow(Status.NOT_FOUND::asRuntimeException))
        .map(account -> LookupUsernameHashResponse.newBuilder()
            .setServiceIdentifier(ServiceIdentifierUtil.toGrpcServiceIdentifier(new AciServiceIdentifier(account.getUuid())))
            .build());
  }

  @Override
  public Mono<LookupUsernameLinkResponse> lookupUsernameLink(final LookupUsernameLinkRequest request) {
    final UUID linkHandle;

    try {
      linkHandle = UUIDUtil.fromByteString(request.getUsernameLinkHandle());
    } catch (final IllegalArgumentException e) {
      throw Status.INVALID_ARGUMENT
          .withDescription("Could not interpret link handle as UUID")
          .withCause(e)
          .asRuntimeException();
    }

    return RateLimitUtil.rateLimitByRemoteAddress(rateLimiters.getUsernameLinkLookupLimiter())
        .then(Mono.fromFuture(() -> accountsManager.getByUsernameLinkHandle(linkHandle)))
        .map(maybeAccount -> maybeAccount
            .flatMap(Account::getEncryptedUsername)
            .orElseThrow(Status.NOT_FOUND::asRuntimeException))
        .map(usernameCiphertext -> LookupUsernameLinkResponse.newBuilder()
            .setUsernameCiphertext(ByteString.copyFrom(usernameCiphertext))
            .build());
  }
}
