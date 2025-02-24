/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import io.grpc.Status;

import java.time.Clock;
import java.util.List;

import org.ghostprotocol.chat.profile.CredentialType;
import org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest;
import org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse;
import org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest;
import org.ghostprotocol.chat.profile.GetUnversionedProfileResponse;
import org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest;
import org.ghostprotocol.chat.profile.GetVersionedProfileResponse;
import org.ghostprotocol.chat.profile.ReactorProfileAnonymousGrpc;
import org.ghostprotocol.libghost.zkgroup.ServerSecretParams;
import org.ghostprotocol.libghost.zkgroup.profiles.ServerZkProfileOperations;
import org.ghostprotocol.service.auth.UnidentifiedAccessUtil;
import org.ghostprotocol.service.badges.ProfileBadgeConverter;
import org.ghostprotocol.service.identity.IdentityType;
import org.ghostprotocol.service.identity.ServiceIdentifier;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.AccountsManager;
import org.ghostprotocol.service.storage.ProfilesManager;
import reactor.core.publisher.Mono;

public class ProfileAnonymousGrpcService extends ReactorProfileAnonymousGrpc.ProfileAnonymousImplBase {
  private final AccountsManager accountsManager;
  private final ProfilesManager profilesManager;
  private final ProfileBadgeConverter profileBadgeConverter;
  private final ServerZkProfileOperations zkProfileOperations;
  private final GroupSendTokenUtil groupSendTokenUtil;

  public ProfileAnonymousGrpcService(
      final AccountsManager accountsManager,
      final ProfilesManager profilesManager,
      final ProfileBadgeConverter profileBadgeConverter,
      final ServerSecretParams serverSecretParams) {
    this.accountsManager = accountsManager;
    this.profilesManager = profilesManager;
    this.profileBadgeConverter = profileBadgeConverter;
    this.zkProfileOperations = new ServerZkProfileOperations(serverSecretParams);
    this.groupSendTokenUtil = new GroupSendTokenUtil(serverSecretParams, Clock.systemUTC());
  }

  @Override
  public Mono<GetUnversionedProfileResponse> getUnversionedProfile(final GetUnversionedProfileAnonymousRequest request) {
    final ServiceIdentifier targetIdentifier =
        ServiceIdentifierUtil.fromGrpcServiceIdentifier(request.getRequest().getServiceIdentifier());

    // Callers must be authenticated to request unversioned profiles by PNI
    if (targetIdentifier.identityType() == IdentityType.PNI) {
      throw Status.UNAUTHENTICATED.asRuntimeException();
    }

    final Mono<Account> account = switch (request.getAuthenticationCase()) {
      case GROUP_SEND_TOKEN ->
          groupSendTokenUtil.checkGroupSendToken(request.getGroupSendToken(), List.of(targetIdentifier))
              .then(Mono.fromFuture(() -> accountsManager.getByServiceIdentifierAsync(targetIdentifier)))
              .flatMap(Mono::justOrEmpty)
              .switchIfEmpty(Mono.error(Status.NOT_FOUND.asException()));
      case UNIDENTIFIED_ACCESS_KEY ->
          getTargetAccountAndValidateUnidentifiedAccess(targetIdentifier, request.getUnidentifiedAccessKey().toByteArray());
      default -> Mono.error(Status.INVALID_ARGUMENT.asException());
    };

    return account.map(targetAccount -> ProfileGrpcHelper.buildUnversionedProfileResponse(targetIdentifier,
            null,
            targetAccount,
            profileBadgeConverter));
  }

  @Override
  public Mono<GetVersionedProfileResponse> getVersionedProfile(final GetVersionedProfileAnonymousRequest request) {
    final ServiceIdentifier targetIdentifier = ServiceIdentifierUtil.fromGrpcServiceIdentifier(request.getRequest().getAccountIdentifier());

    if (targetIdentifier.identityType() != IdentityType.ACI) {
      throw Status.INVALID_ARGUMENT.withDescription("Expected ACI service identifier").asRuntimeException();
    }

    return getTargetAccountAndValidateUnidentifiedAccess(targetIdentifier, request.getUnidentifiedAccessKey().toByteArray())
        .flatMap(targetAccount -> ProfileGrpcHelper.getVersionedProfile(targetAccount, profilesManager, request.getRequest().getVersion()));
  }

  @Override
  public Mono<GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(
      final GetExpiringProfileKeyCredentialAnonymousRequest request) {
    final ServiceIdentifier targetIdentifier = ServiceIdentifierUtil.fromGrpcServiceIdentifier(request.getRequest().getAccountIdentifier());

    if (targetIdentifier.identityType() != IdentityType.ACI) {
      throw Status.INVALID_ARGUMENT.withDescription("Expected ACI service identifier").asRuntimeException();
    }

    if (request.getRequest().getCredentialType() != CredentialType.CREDENTIAL_TYPE_EXPIRING_PROFILE_KEY) {
      throw Status.INVALID_ARGUMENT.withDescription("Expected expiring profile key credential type").asRuntimeException();
    }

    return getTargetAccountAndValidateUnidentifiedAccess(targetIdentifier, request.getUnidentifiedAccessKey().toByteArray())
        .flatMap(account -> ProfileGrpcHelper.getExpiringProfileKeyCredentialResponse(account.getUuid(),
            request.getRequest().getVersion(), request.getRequest().getCredentialRequest().toByteArray(), profilesManager, zkProfileOperations));
  }

  private Mono<Account> getTargetAccountAndValidateUnidentifiedAccess(final ServiceIdentifier targetIdentifier, final byte[] unidentifiedAccessKey) {
    return Mono.fromFuture(() -> accountsManager.getByServiceIdentifierAsync(targetIdentifier))
      .flatMap(Mono::justOrEmpty)
      .filter(targetAccount -> UnidentifiedAccessUtil.checkUnidentifiedAccess(targetAccount, unidentifiedAccessKey))
      .switchIfEmpty(Mono.error(Status.UNAUTHENTICATED.asException()));
  }
}
