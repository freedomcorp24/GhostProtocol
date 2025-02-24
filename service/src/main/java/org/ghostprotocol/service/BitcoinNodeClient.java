package org.ghostprotocol.service.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class BitcoinNodeClient {
  private static final Logger logger = LoggerFactory.getLogger(BitcoinNodeClient.class);
  
  private final HttpClient httpClient;
  private final String nodeUrl;
  private final String rpcUser;
  private final String rpcPassword;

  public BitcoinNodeClient(String nodeUrl, String rpcUser, String rpcPassword) {
    this.httpClient = HttpClient.newHttpClient();
    this.nodeUrl = nodeUrl;
    this.rpcUser = rpcUser;
    this.rpcPassword = rpcPassword;
  }

  public CompletableFuture<String> generateAddress() {
    logger.debug("Generating new Bitcoin address");
    return makeRpcCall("getnewaddress", "")
        .thenApply(response -> {
          logger.debug("Generated Bitcoin address: {}", response.result);
          return response.result;
        })
        .exceptionally(e -> {
          logger.error("Failed to generate Bitcoin address", e);
          throw new RuntimeException("Failed to generate Bitcoin address", e);
        });
  }

  public CompletableFuture<BigDecimal> getBalance(String address) {
    if (address == null || address.isBlank()) {
      throw new IllegalArgumentException("Bitcoin address cannot be null or empty");
    }
    
    logger.debug("Getting balance for Bitcoin address: {}", address);
    return makeRpcCall("getreceivedbyaddress", address)
        .thenApply(response -> {
          BigDecimal balance = new BigDecimal(response.result);
          logger.debug("Balance for {}: {} BTC", address, balance);
          return balance;
        })
        .exceptionally(e -> {
          logger.error("Failed to get balance for address: {}", address, e);
          throw new RuntimeException("Failed to get Bitcoin balance", e);
        });
  }

  public CompletableFuture<Boolean> verifyTransaction(String txHash, int confirmations) {
    if (txHash == null || txHash.isBlank()) {
      throw new IllegalArgumentException("Transaction hash cannot be null or empty");
    }
    if (confirmations < 0) {
      throw new IllegalArgumentException("Confirmations cannot be negative");
    }

    logger.debug("Verifying Bitcoin transaction: {} (required confirmations: {})", txHash, confirmations);
    return makeRpcCall("gettransaction", txHash)
        .thenApply(response -> {
          boolean verified = response.confirmations >= confirmations;
          logger.debug("Transaction {} verification result: {} ({} confirmations)", 
              txHash, verified, response.confirmations);
          return verified;
        })
        .exceptionally(e -> {
          logger.error("Failed to verify transaction: {}", txHash, e);
          throw new RuntimeException("Failed to verify Bitcoin transaction", e);
        });
  }
  
  public CompletableFuture<String> sendToAddress(String fromAddress, String toAddress, BigDecimal amount) {
    if (toAddress == null || toAddress.isBlank()) {
      throw new IllegalArgumentException("Destination address cannot be null or empty");
    }
    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }

    logger.debug("Sending {} BTC to {}", amount, toAddress);
    
    // Format parameters for the RPC call
    String params = String.format("\"%s\", %s", toAddress, amount.toPlainString());
    
    // For Bitcoin, we don't need the fromAddress as the wallet manages the source
    // If specific source address is needed, we would use the "sendfrom" RPC call instead
    return makeRpcCall("sendtoaddress", params)
        .thenApply(response -> {
          logger.debug("Bitcoin transaction sent: {}", response.result);
          return response.result; // This is the transaction hash
        })
        .exceptionally(e -> {
          logger.error("Failed to send Bitcoin", e);
          throw new RuntimeException("Failed to send Bitcoin", e);
        });
  }

  private CompletableFuture<RpcResponse> makeRpcCall(String method, String params) {
    String jsonRequest = String.format(
        "{\"jsonrpc\": \"2.0\", \"id\": \"1\", \"method\": \"%s\", \"params\": [\"%s\"]}", 
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
            logger.error("Bitcoin RPC call failed: {} {}", method, response.statusCode());
            throw new RuntimeException("Bitcoin RPC call failed with status: " + response.statusCode());
          }

          try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            
            if (root.has("error") && !root.get("error").isNull()) {
              String error = root.get("error").get("message").asText();
              logger.error("Bitcoin RPC error: {} - {}", method, error);
              throw new RuntimeException("Bitcoin RPC error: " + error);
            }

            JsonNode result = root.get("result");
            int confirmations = result.has("confirmations") ? result.get("confirmations").asInt() : 0;
            String value = result.isTextual() ? result.asText() : result.toString();

            return new RpcResponse(value, confirmations);
          } catch (JsonProcessingException e) {
            logger.error("Failed to parse Bitcoin RPC response", e);
            throw new RuntimeException("Failed to parse Bitcoin RPC response", e);
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
