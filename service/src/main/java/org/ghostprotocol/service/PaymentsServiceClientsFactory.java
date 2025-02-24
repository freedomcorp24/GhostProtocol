/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;
import org.ghostprotocol.ghostprotocol.currency.CoinGeckoClient;
import org.ghostprotocol.ghostprotocol.currency.FixerClient;
import java.net.http.HttpClient;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = PaymentsServiceClientsConfiguration.class)
public interface PaymentsServiceClientsFactory extends Discoverable {

  FixerClient buildFixerClient(final HttpClient httpClient);

  CoinGeckoClient buildCoinGeckoClient(HttpClient httpClient);
}
