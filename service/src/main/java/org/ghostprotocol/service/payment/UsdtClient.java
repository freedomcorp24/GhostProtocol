package org.ghostprotocol.service.payment;

import org.ghostprotocol.service.http.FaultTolerantHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsdtClient {
    private final FaultTolerantHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String nodeUrl;
    private final String rpcUser;
    private final String rpcPassword;

    public UsdtClient(FaultTolerantHttpClient httpClient, String nodeUrl, String rpcUser, String rpcPassword) {
        this.httpClient = httpClient;
        this.mapper = new ObjectMapper();
        this.nodeUrl = nodeUrl;
        this.rpcUser = rpcUser;
        this.rpcPassword = rpcPassword;
    }

    public String generateAddress() {
        try {
            String response = makeRpcCall("omni_getnewaddress");
            return mapper.readTree(response).get("result").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate USDT address", e);
        }
    }

    public double getBalance(String address) {
        try {
            // Property ID 31 is USDT on Omni Layer
            String response = makeRpcCall("omni_getbalance", address, "31");
            return mapper.readTree(response).get("result").get("balance").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get USDT balance", e);
        }
    }

    public String sendTransaction(String fromAddress, String toAddress, double amount) {
        try {
            // Property ID 31 is USDT on Omni Layer
            String response = makeRpcCall("omni_send", fromAddress, toAddress, "31", String.valueOf(amount));
            return mapper.readTree(response).get("result").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send USDT transaction", e);
        }
    }

    public boolean verifyTransaction(String txHash) {
        try {
            String response = makeRpcCall("omni_gettransaction", txHash);
            JsonNode result = mapper.readTree(response).get("result");
            return result.get("confirmations").asInt() >= 3 && 
                   "valid".equals(result.get("valid").asText());
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify USDT transaction", e);
        }
    }

    private String makeRpcCall(String method, String... params) throws Exception {
        JsonNode request = mapper.createObjectNode()
            .put("jsonrpc", "2.0")
            .put("id", "1")
            .put("method", method)
            .set("params", mapper.valueToTree(params));

        return httpClient.post(nodeUrl, mapper.writeValueAsString(request));
    }
}
