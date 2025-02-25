package org.ghostprotocol.service.payments;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface CryptoNodeClient {
  CompletableFuture<String> generateBtcAddress();
  CompletableFuture<String> generateXmrAddress();
  
  CompletableFuture<Boolean> checkBtcPayment(String address, BigDecimal amount);
  CompletableFuture<Boolean> checkXmrPayment(String address, BigDecimal amount);
  CompletableFuture<Boolean> checkUsdtPayment(String address, BigDecimal amount);
  
  CompletableFuture<Void> transferToHotWallet(String currency, String address, BigDecimal amount);
  CompletableFuture<Void> transferToColdWallet(String currency, String address, BigDecimal amount);
}
