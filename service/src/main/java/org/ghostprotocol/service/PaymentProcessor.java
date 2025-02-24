package org.ghostprotocol.service.payments;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ghostprotocol.service.storage.CryptoTransaction;
import org.ghostprotocol.service.storage.CryptoTransactionStatus;
import org.ghostprotocol.service.storage.CryptoWallet;
import org.ghostprotocol.service.storage.CryptoWalletManager;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PaymentProcessor {
  private static final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

  private final PaymentServerConfiguration config;
  private final ExchangeRateManager exchangeRates;
  private final CryptoNodeClient nodeClient;
  private final CryptoWalletManager walletManager;
  private final ScheduledExecutorService executor;

  public PaymentProcessor(
      PaymentServerConfiguration config,
      ExchangeRateManager exchangeRates,
      CryptoNodeClient nodeClient,
      CryptoWalletManager walletManager,
      ScheduledExecutorService executor) {
    this.config = config;
    this.exchangeRates = exchangeRates;
    this.nodeClient = nodeClient;
    this.walletManager = walletManager;
    this.executor = executor;
  }

  public CompletableFuture<String> generatePaymentAddress(String currency, UUID accountId) {
    if (currency == null || currency.isBlank()) {
      return CompletableFuture.failedFuture(
          new IllegalArgumentException("Currency cannot be null or empty"));
    }

    logger.debug("Generating payment address for currency: {} account: {}", currency, accountId);

    return switch (currency.toUpperCase()) {
      case "BTC" -> generateBtcAddress(accountId);
      case "XMR" -> generateXmrAddress(accountId);
      case "USDT" -> generateUsdtAddress(accountId);
      default -> CompletableFuture.failedFuture(
          new IllegalArgumentException("Unsupported currency: " + currency));
    };
  }

  private CompletableFuture<String> generateBtcAddress(UUID accountId) {
    return nodeClient.generateBtcAddress()
        .thenCompose(address -> createWallet(accountId, "BTC", address));
  }

  private CompletableFuture<String> generateXmrAddress(UUID accountId) {
    return nodeClient.generateXmrAddress()
        .thenCompose(address -> createWallet(accountId, "XMR", address));
  }

  private CompletableFuture<String> generateUsdtAddress(UUID accountId) {
    String address = config.getHotWallet().getEthAddress();
    return createWallet(accountId, "USDT", address);
  }

  private CompletableFuture<String> createWallet(UUID accountId, String currency, String address) {
    CryptoWallet wallet = new CryptoWallet(
        UUID.randomUUID(),
        accountId,
        currency,
        address,
        Instant.now().plus(config.getCryptoNode().getPaymentTimeout()));

    return CompletableFuture.supplyAsync(() -> {
      walletManager.create(wallet);
      scheduleWalletCleanup(wallet);
      logger.debug("Created {} wallet for account {}: {}", currency, accountId, wallet.getWalletId());
      return address;
    }, executor);
  }

  private void scheduleWalletCleanup(CryptoWallet wallet) {
    Duration timeout = config.getCryptoNode().getPaymentTimeout();
    executor.schedule(() -> {
      try {
        Optional<CryptoWallet> existing = walletManager.get(wallet.getWalletId());
        if (existing.isPresent() && existing.get().getExpiresAt().equals(wallet.getExpiresAt())) {
          walletManager.delete(wallet.getWalletId());
          logger.debug("Cleaned up expired wallet: {}", wallet.getWalletId());
        }
      } catch (Exception e) {
        logger.error("Failed to cleanup expired wallet: {}", wallet.getWalletId(), e);
      }
    }, timeout.toMillis(), TimeUnit.MILLISECONDS);
  }

  public CompletableFuture<Boolean> checkPayment(
      String currency, String address, BigDecimal expectedAmount) {
    if (currency == null || currency.isBlank()) {
      return CompletableFuture.failedFuture(
          new IllegalArgumentException("Currency cannot be null or empty"));
    }
    if (address == null || address.isBlank()) {
      return CompletableFuture.failedFuture(
          new IllegalArgumentException("Address cannot be null or empty"));
    }
    if (expectedAmount == null || expectedAmount.compareTo(BigDecimal.ZERO) <= 0) {
      return CompletableFuture.failedFuture(
          new IllegalArgumentException("Expected amount must be positive"));
    }

    logger.debug("Checking payment of {} {} to address: {}", expectedAmount, currency, address);

    return switch (currency.toUpperCase()) {
      case "BTC" -> checkBtcPayment(address, expectedAmount);
      case "XMR" -> checkXmrPayment(address, expectedAmount);
      case "USDT" -> checkUsdtPayment(address, expectedAmount);
      default -> CompletableFuture.failedFuture(
          new IllegalArgumentException("Unsupported currency: " + currency));
    };
  }

  private CompletableFuture<Boolean> checkBtcPayment(String address, BigDecimal expectedAmount) {
    return nodeClient.checkBtcPayment(address, expectedAmount)
        .thenCompose(received -> handlePaymentReceived("BTC", address, expectedAmount, received));
  }

  private CompletableFuture<Boolean> checkXmrPayment(String address, BigDecimal expectedAmount) {
    return nodeClient.checkXmrPayment(address, expectedAmount)
        .thenCompose(received -> handlePaymentReceived("XMR", address, expectedAmount, received));
  }

  private CompletableFuture<Boolean> checkUsdtPayment(String address, BigDecimal expectedAmount) {
    return nodeClient.checkUsdtPayment(address, expectedAmount)
        .thenCompose(received -> handlePaymentReceived("USDT", address, expectedAmount, received));
  }

  private CompletableFuture<Boolean> handlePaymentReceived(
      String currency, String address, BigDecimal expectedAmount, boolean received) {
    if (!received) {
      return CompletableFuture.completedFuture(false);
    }

    return CompletableFuture.supplyAsync(() -> {
      Optional<CryptoWallet> wallet = walletManager.getByAddress(currency, address);
      if (wallet.isEmpty()) {
        logger.warn("Received payment to unknown wallet: {} {}", currency, address);
        return false;
      }

      CryptoTransaction transaction = new CryptoTransaction(
          UUID.randomUUID(),
          wallet.get().getWalletId(),
          expectedAmount,
          CryptoTransactionStatus.COMPLETED,
          Instant.now());

      walletManager.addTransaction(transaction);
      logger.info("Payment received: {} {} to wallet {}", 
          expectedAmount, currency, wallet.get().getWalletId());

      // Schedule transfer to hot wallet
      scheduleHotWalletTransfer(currency, address, expectedAmount);

      return true;
    }, executor);
  }

  private void scheduleHotWalletTransfer(String currency, String address, BigDecimal amount) {
    executor.schedule(() -> {
      try {
        nodeClient.transferToHotWallet(currency, address, amount)
            .thenRun(() -> logger.info("Transferred {} {} to hot wallet", amount, currency))
            .exceptionally(e -> {
              logger.error("Failed to transfer to hot wallet: {} {}", currency, address, e);
              return null;
            });
      } catch (Exception e) {
        logger.error("Failed to schedule hot wallet transfer: {} {}", currency, address, e);
      }
    }, config.getCryptoNode().getRetryDelay().toMillis(), TimeUnit.MILLISECONDS);
  }
}
