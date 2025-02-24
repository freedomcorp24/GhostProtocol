/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.grpc;

import static java.util.Objects.requireNonNull;

import io.grpc.Status;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.tuple.Pair;
import org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest;
import org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse;
import org.ghostprotocol.chat.payments.ReactorPaymentsGrpc;
import org.ghostprotocol.service.auth.grpc.AuthenticationUtil;
import org.ghostprotocol.service.currency.CurrencyConversionManager;
import org.ghostprotocol.service.entities.CurrencyConversionEntityList;
import reactor.core.publisher.Mono;

public class PaymentsGrpcService extends ReactorPaymentsGrpc.PaymentsImplBase {

  private final CurrencyConversionManager currencyManager;


  public PaymentsGrpcService(final CurrencyConversionManager currencyManager) {
    this.currencyManager = requireNonNull(currencyManager);
  }

  @Override
  public Mono<GetCurrencyConversionsResponse> getCurrencyConversions(final GetCurrencyConversionsRequest request) {
    AuthenticationUtil.requireAuthenticatedDevice();

    final CurrencyConversionEntityList currencyConversionEntityList = currencyManager
        .getCurrencyConversions()
        .orElseThrow(Status.UNAVAILABLE::asRuntimeException);

    final List<GetCurrencyConversionsResponse.CurrencyConversionEntity> currencyConversionEntities = currencyConversionEntityList
        .getCurrencies()
        .stream()
        .map(cce -> GetCurrencyConversionsResponse.CurrencyConversionEntity.newBuilder()
            .setBase(cce.getBase())
            .putAllConversions(transformBigDecimalsToStrings(cce.getConversions()))
            .build())
        .toList();

    return Mono.just(GetCurrencyConversionsResponse.newBuilder()
        .addAllCurrencies(currencyConversionEntities).setTimestamp(currencyConversionEntityList.getTimestamp())
        .build());
  }

  @Nonnull
  private static Map<String, String> transformBigDecimalsToStrings(final Map<String, BigDecimal> conversions) {
    AuthenticationUtil.requireAuthenticatedDevice();
    return conversions.entrySet().stream()
        .map(e -> Pair.of(e.getKey(), e.getValue().toString()))
        .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
  }
}
