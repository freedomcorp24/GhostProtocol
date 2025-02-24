package org.ghostprotocol.service.payment;

import org.ghostprotocol.service.storage.Account;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CryptoTransactionManager {
    private final CryptoWalletService walletService;
    private final CryptoPaymentService paymentService;

    public CryptoTransactionManager(
            CryptoWalletService walletService,
            CryptoPaymentService paymentService) {
        this.walletService = walletService;
        this.paymentService = paymentService;
    }

    public CompletableFuture<PaymentTransaction> initiatePayment(
            Account sender,
            String recipientAddress,
            double amount,
            String currency) {
        
        return CompletableFuture.supplyAsync(() -> {
            // Verify sufficient balance
            double balance = walletService.getBalance(sender, currency);
            if (balance < amount) {
                throw new InsufficientBalanceException("Insufficient balance for transaction");
            }

            // Send payment
            walletService.sendPayment(sender, recipientAddress, amount, currency);
            
            // Create and return transaction record
            return new PaymentTransaction(
                sender.getUuid(),
                recipientAddress,
                amount,
                currency,
                System.currentTimeMillis()
            );
        });
    }

    public List<PaymentTransaction> getTransactionHistory(Account account, String currency) {
        return paymentService.getTransactionHistory(account.getUuid(), currency);
    }

    public PaymentTransaction getTransaction(String txHash, String currency) {
        return paymentService.getTransaction(txHash);
    }

    public static class InsufficientBalanceException extends RuntimeException {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }
}
