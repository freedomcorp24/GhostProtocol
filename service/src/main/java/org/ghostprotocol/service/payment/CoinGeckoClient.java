package org.ghostprotocol.service.payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ghostprotocol.service.http.FaultTolerantHttpClient;

import java.util.HashMap;
import java.util.Map;

public class CoinGeckoClient {
    private static final String API_BASE = "https://api.coingecko.com/api/v3";
    private final FaultTolerantHttpClient httpClient;
    private final ObjectMapper mapper;

    public CoinGeckoClient(FaultTolerantHttpClient httpClient) {
        this.httpClient = httpClient;
        this.mapper = new ObjectMapper();
    }

    public Map<String, Double> getUSDRates() throws Exception {
        Map<String, Double> rates = new HashMap<>();
        
        String response = httpClient.get(API_BASE + "/simple/price?ids=bitcoin,monero,tether&vs_currencies=usd");
        JsonNode root = mapper.readTree(response);

        rates.put("BTC", root.get("bitcoin").get("usd").asDouble());
        rates.put("XMR", root.get("monero").get("usd").asDouble());
        rates.put("USDT", root.get("tether").get("usd").asDouble());

        return rates;
    }
}
