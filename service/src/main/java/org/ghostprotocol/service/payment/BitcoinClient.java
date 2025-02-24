package org.ghostprotocol.service.payment;

import org.ghostprotocol.service.http.FaultTolerantHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BitcoinClient {
    private final FaultTolerantHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String nodeUrl;
    private final String rpcUser;
    private final String rpcPassword;

    public BitcoinClient(FaultTolerantHttpClient httpClient, String nodeUrl, String rpcUser, String rpcPassword) {
        this.httpClient = httpClient;
        this.mapper = new ObjectMapper();
        this.nodeUrl = nodeUrl;
        this.rpcUser = rpcUser;
        this.rpcPassword = rpcPassword;
    }

    public String generateAddress() {
        try {
            String response = makeRpcCall("getnewaddress");
            return mapper.readTree(response).get("result").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Bitcoin address", e);
        }
    }

    public double getBalance(String address) {
        try {
            String response = makeRpcCall("getreceivedbyaddress", address);
            return mapper.readTree(response).get("result").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get Bitcoin balance", e);
        }
    }

    public String sendTransaction(String fromAddress, String toAddress, double amount) {
        try {
            String response = makeRpcCall("sendtoaddress", toAddress, String.valueOf(amount));
            return mapper.readTree(response).get("result").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send Bitcoin transaction", e);
        }
    }

    public boolean verifyTransaction(String txHash) {
        try {
            String response = makeRpcCall("gettransaction", txHash);
            JsonNode result = mapper.readTree(response).get("result");
            return result.get("confirmations").asInt() >= 3;
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify Bitcoin transaction", e);
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
