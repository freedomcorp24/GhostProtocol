package org.whispersystems.textsecuregcm.payments;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public class CryptoNodeClientImpl implements CryptoNodeClient {
  private final BitcoinNodeClient btcClient;
  private final MoneroNodeClient xmrClient;
  private final UsdtNodeClient usdtClient;
  private final PaymentServerConfiguration config;

  public CryptoNodeClientImpl(
      BitcoinNodeClient btcClient,
      MoneroNodeClient xmrClient, 
      UsdtNodeClient usdtClient,
      PaymentServerConfiguration config) {
    this.btcClient = btcClient;
    this.xmrClient = xmrClient;
    this.usdtClient = usdtClient;
    this.config = config;
  }

  @Override
  public CompletableFuture<String> generateBtcAddress() {
    return btcClient.generateAddress();
  }

  @Override
  public CompletableFuture<String> generateXmrAddress() {
    return xmrClient.generateAddress();
  }

  @Override
  public CompletableFuture<Boolean> checkBtcPayment(String address, BigDecimal amount) {
    return btcClient.getBalance(address)
        .thenApply(balance -> balance.compareTo(amount) >= 0);
  }

  @Override
  public CompletableFuture<Boolean> checkXmrPayment(String address, BigDecimal amount) {
    return xmrClient.getBalance(address)
        .thenApply(balance -> balance.compareTo(amount) >= 0);
  }

  @Override
  public CompletableFuture<Boolean> checkUsdtPayment(String address, BigDecimal amount) {
    return usdtClient.getBalance(address)
        .thenApply(balance -> balance.compareTo(amount) >= 0);
  }

  @Override
  public CompletableFuture<Void> transferToHotWallet(String currency, String address, BigDecimal amount) {
    // Implementation would handle transferring funds to hot wallet
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public CompletableFuture<Void> transferToColdWallet(String currency, String address, BigDecimal amount) {
    // Implementation would handle transferring funds to cold storage
    return CompletableFuture.completedFuture(null);
  }
}
