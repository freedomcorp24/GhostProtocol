package org.whispersystems.textsecuregcm.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MoneroNodeClient {
  private static final Logger logger = LoggerFactory.getLogger(MoneroNodeClient.class);
  
  private final HttpClient httpClient;
  private final String nodeUrl;
  private final String rpcUser;
  private final String rpcPassword;

  public MoneroNodeClient(String nodeUrl, String rpcUser, String rpcPassword) {
    this.httpClient = HttpClient.newHttpClient();
    this.nodeUrl = nodeUrl;
    this.rpcUser = rpcUser;
    this.rpcPassword = rpcPassword;
  }

  public CompletableFuture<String> generateAddress() {
    logger.debug("Generating new Monero address");
    return makeRpcCall("create_address", "{\"account_index\":0}")
        .thenApply(response -> {
          logger.debug("Generated Monero address: {}", response.result);
          return response.result;
        })
        .exceptionally(e -> {
          logger.error("Failed to generate Monero address", e);
          throw new RuntimeException("Failed to generate Monero address", e);
        });
  }

  public CompletableFuture<BigDecimal> getBalance(String address) {
    if (address == null || address.isBlank()) {
      throw new IllegalArgumentException("Monero address cannot be null or empty");
    }

    logger.debug("Getting balance for Monero address: {}", address);
    return makeRpcCall("get_address_balance",
        String.format("{\"address\":\"%s\"}", address))
        .thenApply(response -> {
          BigDecimal balance = new BigDecimal(response.result);
          logger.debug("Balance for {}: {} XMR", address, balance);
          return balance;
        })
        .exceptionally(e -> {
          logger.error("Failed to get balance for address: {}", address, e);
          throw new RuntimeException("Failed to get Monero balance", e);
        });
  }

  public CompletableFuture<Boolean> verifyTransaction(String txHash, int confirmations) {
    if (txHash == null || txHash.isBlank()) {
      throw new IllegalArgumentException("Transaction hash cannot be null or empty");
    }
    if (confirmations < 0) {
      throw new IllegalArgumentException("Confirmations cannot be negative");
    }

    logger.debug("Verifying Monero transaction: {} (required confirmations: {})", txHash, confirmations);
    return makeRpcCall("get_transfer_by_txid",
        String.format("{\"txid\":\"%s\"}", txHash))
        .thenApply(response -> {
          boolean verified = response.confirmations >= confirmations;
          logger.debug("Transaction {} verification result: {} ({} confirmations)",
              txHash, verified, response.confirmations);
          return verified;
        })
        .exceptionally(e -> {
          logger.error("Failed to verify transaction: {}", txHash, e);
          throw new RuntimeException("Failed to verify Monero transaction", e);
        });
  }

  private CompletableFuture<RpcResponse> makeRpcCall(String method, String params) {
    String jsonRequest = String.format(
        "{\"jsonrpc\": \"2.0\", \"id\": \"1\", \"method\": \"%s\", \"params\": %s}",
        method, params);

    String auth = Base64.getEncoder().encodeToString((rpcUser + ":" + rpcPassword).getBytes());

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(nodeUrl))
        .header("Content-Type", "application/json")
        .header("Authorization", "Basic " + auth)
        .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
        .build();

    return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(response -> {
          if (response.statusCode() != 200) {
            logger.error("Monero RPC call failed: {} {}", method, response.statusCode());
            throw new RuntimeException("Monero RPC call failed with status: " + response.statusCode());
          }

          try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            
            if (root.has("error") && !root.get("error").isNull()) {
              String error = root.get("error").get("message").asText();
              logger.error("Monero RPC error: {} - {}", method, error);
              throw new RuntimeException("Monero RPC error: " + error);
            }

            JsonNode result = root.get("result");
            int confirmations = result.has("confirmations") ? result.get("confirmations").asInt() : 0;
            String value = result.isTextual() ? result.asText() : result.toString();

            return new RpcResponse(value, confirmations);
          } catch (JsonProcessingException e) {
            logger.error("Failed to parse Monero RPC response", e);
            throw new RuntimeException("Failed to parse Monero RPC response", e);
          }
        });
  }

  private static class RpcResponse {
    @JsonProperty public final String result;
    @JsonProperty public final int confirmations;

    public RpcResponse(String result, int confirmations) {
      this.result = result;
      this.confirmations = confirmations;
    }
  }
}
