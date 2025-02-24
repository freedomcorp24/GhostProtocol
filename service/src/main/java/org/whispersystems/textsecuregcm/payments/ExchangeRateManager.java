package org.whispersystems.textsecuregcm.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExchangeRateManager implements Managed {
  private static final Logger logger = LoggerFactory.getLogger(ExchangeRateManager.class);
  private static final Duration UPDATE_INTERVAL = Duration.ofMinutes(15);
  private static final String COINGECKO_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,monero,tether&vs_currencies=usd";

  private final HttpClient httpClient;
  private final ScheduledExecutorService scheduler;
  private final Map<String, BigDecimal> rates;

  public ExchangeRateManager() {
    this.httpClient = HttpClient.newHttpClient();
    this.scheduler = Executors.newSingleThreadScheduledExecutor();
    this.rates = new ConcurrentHashMap<>();
  }

  @Override
  public void start() {
    scheduler.scheduleAtFixedRate(this::updateRates,
        0, UPDATE_INTERVAL.toMinutes(), TimeUnit.MINUTES);
  }

  @Override
  public void stop() {
    scheduler.shutdown();
  }

  private void updateRates() {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(COINGECKO_API))
          .build();

      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());

      // Parse response and update rates
      // Implementation will parse JSON response and update rates map
      
    } catch (Exception e) {
      logger.error("Failed to update exchange rates", e);
    }
  }

  public BigDecimal getRate(String currency) {
    return rates.getOrDefault(currency, BigDecimal.ZERO);
  }
}
