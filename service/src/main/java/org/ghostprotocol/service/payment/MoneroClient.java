package org.ghostprotocol.service.payment;

import org.ghostprotocol.service.http.FaultTolerantHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MoneroClient {
    private final FaultTolerantHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String nodeUrl;
    private final String walletUrl;
    private final String rpcUser;
    private final String rpcPassword;

    public MoneroClient(
            FaultTolerantHttpClient httpClient,
            String nodeUrl,
            String walletUrl,
            String rpcUser,
            String rpcPassword) {
        this.httpClient = httpClient;
        this.mapper = new ObjectMapper();
        this.nodeUrl = nodeUrl;
        this.walletUrl = walletUrl;
        this.rpcUser = rpcUser;
        this.rpcPassword = rpcPassword;
    }

    public String generateAddress() {
        try {
            String response = makeWalletRpcCall("create_address");
            return mapper.readTree(response).get("result").get("address").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Monero address", e);
        }
    }

    public double getBalance(String address) {
        try {
            String response = makeWalletRpcCall("get_address_balance", address);
            long atomic = mapper.readTree(response).get("result").get("balance").asLong();
            return atomic / 1e12; // Convert from piconero to XMR
        } catch (Exception e) {
            throw new RuntimeException("Failed to get Monero balance", e);
        }
    }

    public String sendTransaction(String toAddress, double amount) {
        try {
            long atomic = (long)(amount * 1e12); // Convert XMR to piconero
            String response = makeWalletRpcCall("transfer", toAddress, String.valueOf(atomic));
            return mapper.readTree(response).get("result").get("tx_hash").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send Monero transaction", e);
        }
    }

    public boolean verifyTransaction(String txHash) {
        try {
            String response = makeNodeRpcCall("get_transactions", txHash);
            JsonNode result = mapper.readTree(response).get("result");
            return result.get("confirmations").asInt() >= 10;
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify Monero transaction", e);
        }
    }

    private String makeWalletRpcCall(String method, String... params) throws Exception {
        JsonNode request = mapper.createObjectNode()
            .put("jsonrpc", "2.0")
            .put("id", "1")
            .put("method", method)
            .set("params", mapper.valueToTree(params));

        return httpClient.post(walletUrl, mapper.writeValueAsString(request));
    }

    private String makeNodeRpcCall(String method, String... params) throws Exception {
        JsonNode request = mapper.createObjectNode()
            .put("jsonrpc", "2.0")
            .put("id", "1")
            .put("method", method)
            .set("params", mapper.valueToTree(params));

        return httpClient.post(nodeUrl, mapper.writeValueAsString(request));
    }
}
