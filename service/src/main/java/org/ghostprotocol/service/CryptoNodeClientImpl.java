package org.ghostprotocol.service.payments;

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
    if (currency == null || currency.isBlank()) {
      throw new IllegalArgumentException("Currency cannot be null or empty");
    }
    if (address == null || address.isBlank()) {
      throw new IllegalArgumentException("Address cannot be null or empty");
    }
    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }
    
    String hotWalletAddress;
    
    switch (currency.toUpperCase()) {
      case "BTC":
        hotWalletAddress = config.getHotWallet().getBtcAddress();
        return btcClient.sendToAddress(address, hotWalletAddress, amount)
            .thenApply(txHash -> null);
      case "XMR":
        hotWalletAddress = config.getHotWallet().getXmrAddress();
        return xmrClient.sendToAddress(address, hotWalletAddress, amount)
            .thenApply(txHash -> null);
      case "USDT":
        hotWalletAddress = config.getHotWallet().getUsdtAddress();
        return usdtClient.sendToAddress(address, hotWalletAddress, amount)
            .thenApply(txHash -> null);
      default:
        return CompletableFuture.failedFuture(
            new IllegalArgumentException("Unsupported currency: " + currency));
    }
  }

  @Override
  public CompletableFuture<Void> transferToColdWallet(String currency, String address, BigDecimal amount) {
    if (currency == null || currency.isBlank()) {
      throw new IllegalArgumentException("Currency cannot be null or empty");
    }
    if (address == null || address.isBlank()) {
      throw new IllegalArgumentException("Address cannot be null or empty");
    }
    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }
    
    String coldWalletAddress;
    
    switch (currency.toUpperCase()) {
      case "BTC":
        coldWalletAddress = config.getColdWallet().getBtcAddress();
        return btcClient.sendToAddress(address, coldWalletAddress, amount)
            .thenApply(txHash -> null);
      case "XMR":
        coldWalletAddress = config.getColdWallet().getXmrAddress();
        return xmrClient.sendToAddress(address, coldWalletAddress, amount)
            .thenApply(txHash -> null);
      case "USDT":
        coldWalletAddress = config.getColdWallet().getUsdtAddress();
        return usdtClient.sendToAddress(address, coldWalletAddress, amount)
            .thenApply(txHash -> null);
      default:
        return CompletableFuture.failedFuture(
            new IllegalArgumentException("Unsupported currency: " + currency));
    }
  }
}
