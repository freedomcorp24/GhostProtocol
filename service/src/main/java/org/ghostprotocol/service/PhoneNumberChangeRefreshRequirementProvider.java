/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.auth;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.ghostprotocol.service.storage.AccountsManager;
import org.ghostprotocol.service.util.Pair;

public class PhoneNumberChangeRefreshRequirementProvider implements WebsocketRefreshRequirementProvider {

  private static final String ACCOUNT_UUID =
      PhoneNumberChangeRefreshRequirementProvider.class.getName() + ".accountUuid";

  private static final String INITIAL_NUMBER_KEY =
      PhoneNumberChangeRefreshRequirementProvider.class.getName() + ".initialNumber";
  private final AccountsManager accountsManager;

  public PhoneNumberChangeRefreshRequirementProvider(final AccountsManager accountsManager) {
    this.accountsManager = accountsManager;
  }

  @Override
  public void handleRequestFiltered(final RequestEvent requestEvent) {
    if (requestEvent.getUriInfo().getMatchedResourceMethod().getInvocable().getHandlingMethod()
        .getAnnotation(ChangesPhoneNumber.class) == null) {
      return;
    }
    ContainerRequestUtil.getAuthenticatedAccount(requestEvent.getContainerRequest())
        .ifPresent(account -> {
          requestEvent.getContainerRequest().setProperty(INITIAL_NUMBER_KEY, account.e164());
          requestEvent.getContainerRequest().setProperty(ACCOUNT_UUID, account.accountId());
        });
  }

  @Override
  public List<Pair<UUID, Byte>> handleRequestFinished(final RequestEvent requestEvent) {
    final String initialNumber = (String) requestEvent.getContainerRequest().getProperty(INITIAL_NUMBER_KEY);

    if (initialNumber == null) {
      return Collections.emptyList();
    }
    return accountsManager.getByAccountIdentifier((UUID) requestEvent.getContainerRequest().getProperty(ACCOUNT_UUID))
        .filter(account -> !initialNumber.equals(account.getNumber()))
        .map(account -> account.getDevices().stream()
            .map(device -> new Pair<>(account.getUuid(), device.getId()))
            .collect(Collectors.toList()))
        .orElse(Collections.emptyList());
  }
}
