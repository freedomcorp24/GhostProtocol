package org.ghostprotocol.service.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UsdtNodeClient {
  private static final Logger logger = LoggerFactory.getLogger(UsdtNodeClient.class);
  
  private final Web3j web3j;
  private final String contractAddress;
  private final String nodeUrl;

  public UsdtNodeClient(String nodeUrl, String contractAddress) {
    this.web3j = Web3j.build(new HttpService(nodeUrl));
    this.nodeUrl = nodeUrl;
    this.contractAddress = contractAddress;
  }

  public CompletableFuture<BigDecimal> getBalance(String address) {
    if (address == null || address.isBlank()) {
      throw new IllegalArgumentException("ETH address cannot be null or empty");
    }

    logger.debug("Getting USDT balance for address: {}", address);
    return CompletableFuture.supplyAsync(() -> {
      try {
        Function function = new Function(
            "balanceOf",
            List.of(new Address(address)),
            List.of(new TypeReference<Uint256>() {}));

        String encodedFunction = FunctionEncoder.encode(function);
        
        org.web3j.protocol.core.methods.request.Transaction transaction =
            org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
                address, contractAddress, encodedFunction);

        String value = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST)
            .send()
            .getValue();

        BigInteger balance = (BigInteger) FunctionReturnDecoder.decode(
            value, function.getOutputParameters()).get(0).getValue();

        BigDecimal usdtBalance = new BigDecimal(balance)
            .divide(BigDecimal.valueOf(1000000)); // 6 decimals for USDT

        logger.debug("USDT balance for {}: {}", address, usdtBalance);
        return usdtBalance;
      } catch (Exception e) {
        logger.error("Failed to get USDT balance for address: {}", address, e);
        throw new RuntimeException("Failed to get USDT balance", e);
      }
    });
  }

  public CompletableFuture<Boolean> verifyTransaction(String txHash, int confirmations) {
    if (txHash == null || txHash.isBlank()) {
      throw new IllegalArgumentException("Transaction hash cannot be null or empty");
    }
    if (confirmations < 0) {
      throw new IllegalArgumentException("Confirmations cannot be negative");
    }

    logger.debug("Verifying USDT transaction: {} (required confirmations: {})", txHash, confirmations);
    return CompletableFuture.supplyAsync(() -> {
      try {
        TransactionReceipt receipt = web3j.ethGetTransactionReceipt(txHash)
            .send()
            .getTransactionReceipt()
            .orElseThrow(() -> new RuntimeException("Transaction receipt not found"));

        BigInteger currentBlock = web3j.ethBlockNumber()
            .send()
            .getBlockNumber();

        BigInteger txBlock = receipt.getBlockNumber();
        int confirmedBlocks = currentBlock.subtract(txBlock).intValue();

        boolean verified = confirmedBlocks >= confirmations;
        logger.debug("Transaction {} verification result: {} ({} confirmations)",
            txHash, verified, confirmedBlocks);
        return verified;
      } catch (Exception e) {
        logger.error("Failed to verify USDT transaction: {}", txHash, e);
        throw new RuntimeException("Failed to verify USDT transaction", e);
      }
    });
  }

  public void shutdown() {
    web3j.shutdown();
  }
}
