package org.ghostprotocol.service.payment;

import org.ghostprotocol.service.storage.Account;
import java.util.UUID;

public class CryptoWalletService {
    private final HDWalletManager hdWalletManager;
    private final BlockExplorerClient blockExplorerClient;
    private final CryptoPaymentService paymentService;

    public CryptoWalletService(
            HDWalletManager hdWalletManager,
            BlockExplorerClient blockExplorerClient,
            CryptoPaymentService paymentService) {
        this.hdWalletManager = hdWalletManager;
        this.blockExplorerClient = blockExplorerClient;
        this.paymentService = paymentService;
    }

    public PaymentAddress createPaymentAddress(Account account, String currency) {
        String address = hdWalletManager.generateAddress(account.getUuid(), currency);
        return new PaymentAddress(address, currency);
    }

    public void processIncomingPayment(String txHash, String currency) {
        PaymentTransaction tx = blockExplorerClient.getTransaction(txHash, currency);
        if (tx.isValid() && !tx.isProcessed()) {
            paymentService.processPayment(tx);
        }
    }

    public void sendPayment(Account sender, String recipientAddress, double amount, String currency) {
        PaymentTransaction tx = hdWalletManager.createTransaction(
            sender.getUuid(),
            recipientAddress,
            amount,
            currency
        );
        paymentService.sendPayment(tx);
    }

    public double getBalance(Account account, String currency) {
        return hdWalletManager.getBalance(account.getUuid(), currency);
    }
}
