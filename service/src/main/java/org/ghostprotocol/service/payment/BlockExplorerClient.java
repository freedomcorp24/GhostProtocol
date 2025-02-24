package org.ghostprotocol.service.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class BlockExplorerClient {
    private static final Logger logger = LoggerFactory.getLogger(BlockExplorerClient.class);
    private static final String BTC_API_URL = "https://api.blockchair.com/bitcoin";
    private static final String XMR_API_URL = "https://api.blockchair.com/monero";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Inject
    public BlockExplorerClient(ObjectMapper objectMapper) {
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(TIMEOUT)
            .build();
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<BigDecimal> getConfirmedBalance(String address) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BTC_API_URL + "/dashboards/address/" + address))
                    .timeout(TIMEOUT)
                    .build();

                HttpResponse<String> response = httpClient.send(request, 
                    HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    throw new RuntimeException("Failed to get balance: " + response.body());
                }

                // Parse response and extract confirmed balance
                return BigDecimal.ZERO; // TODO: Implement response parsing
            } catch (Exception e) {
                logger.error("Failed to get balance for address: " + address, e);
                throw new RuntimeException("Failed to get balance", e);
            }
        });
    }

    public CompletableFuture<Integer> getTransactionConfirmations(String txHash) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BTC_API_URL + "/dashboards/transaction/" + txHash))
                    .timeout(TIMEOUT)
                    .build();

                HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    throw new RuntimeException("Failed to get transaction: " + response.body());
                }

                // Parse response and extract confirmations
                return 0; // TODO: Implement response parsing
            } catch (Exception e) {
                logger.error("Failed to get confirmations for tx: " + txHash, e);
                throw new RuntimeException("Failed to get confirmations", e);
            }
        });
    }
}
