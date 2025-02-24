package org.ghostprotocol.service.payment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExchangeRateManager {
    private final Map<String, Double> rates;
    private final CoinGeckoClient coinGeckoClient;
    private final ScheduledExecutorService scheduler;

    public ExchangeRateManager(CoinGeckoClient coinGeckoClient, ScheduledExecutorService scheduler) {
        this.rates = new ConcurrentHashMap<>();
        this.coinGeckoClient = coinGeckoClient;
        this.scheduler = scheduler;
        
        // Update rates every 5 minutes
        scheduler.scheduleAtFixedRate(this::updateRates, 0, 5, TimeUnit.MINUTES);
    }

    public double getRate(String currency) {
        return rates.getOrDefault(currency, 0.0);
    }

    public double convertToUSD(double amount, String fromCurrency) {
        double rate = getRate(fromCurrency);
        return amount * rate;
    }

    public double convertFromUSD(double usdAmount, String toCurrency) {
        double rate = getRate(toCurrency);
        return rate > 0 ? usdAmount / rate : 0;
    }

    private void updateRates() {
        try {
            Map<String, Double> newRates = coinGeckoClient.getUSDRates();
            rates.clear();
            rates.putAll(newRates);
        } catch (Exception e) {
            // Log error but keep existing rates
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
