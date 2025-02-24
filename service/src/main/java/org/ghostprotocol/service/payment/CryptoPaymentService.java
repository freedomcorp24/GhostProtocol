package org.ghostprotocol.service.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ghostprotocol.service.storage.DynamoDbTable;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CryptoPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(CryptoPaymentService.class);
    private static final Duration ADDRESS_VALIDITY = Duration.ofHours(2);
    private static final int REQUIRED_CONFIRMATIONS = 3;

    private final DynamoDbTable<PaymentAddress> addressTable;
    private final DynamoDbTable<PaymentTransaction> transactionTable;
    private final HDWalletManager walletManager;
    private final BlockExplorerClient explorerClient;
    private final Clock clock;
    private final ScheduledExecutorService executor;

    @Inject
    public CryptoPaymentService(
            DynamoDbTable<PaymentAddress> addressTable,
            DynamoDbTable<PaymentTransaction> transactionTable,
            HDWalletManager walletManager,
            BlockExplorerClient explorerClient,
            Clock clock,
            ScheduledExecutorService executor) {
        this.addressTable = addressTable;
        this.transactionTable = transactionTable;
        this.walletManager = walletManager;
        this.explorerClient = explorerClient;
        this.clock = clock;
        this.executor = executor;

        // Start periodic cleanup of expired addresses
        executor.scheduleAtFixedRate(
            () -> walletManager.cleanupExpiredAddresses(),
            1, 1, TimeUnit.HOURS
        );
    }

    public CompletableFuture<PaymentAddress> generatePaymentAddress(
            UUID userId, String currency, BigDecimal amount) {
        return walletManager.generateAddress(userId, currency)
            .thenApply(address -> {
                Instant now = clock.instant();
                PaymentAddress paymentAddress = new PaymentAddress(
                    UUID.randomUUID(),
                    userId,
                    currency,
                    address,
                    amount,
                    "PENDING",
                    now,
                    now.plus(ADDRESS_VALIDITY)
                );
                addressTable.put(paymentAddress);
                return paymentAddress;
            });
    }

    public CompletableFuture<Boolean> verifyPayment(UUID paymentAddressId) {
        return CompletableFuture.supplyAsync(() -> {
            PaymentAddress address = addressTable.get(paymentAddressId.toString());
            if (address == null) {
                throw new IllegalArgumentException("Payment address not found");
            }

            if (clock.instant().isAfter(address.getExpiresAt())) {
                return false;
            }

            return explorerClient.getConfirmedBalance(address.getAddress())
                .thenApply(balance -> {
                    if (balance.compareTo(address.getAmount()) >= 0) {
                        // Payment received, update status
                        PaymentAddress updatedAddress = new PaymentAddress(
                            address.getId(),
                            address.getUserId(),
                            address.getCurrency(),
                            address.getAddress(),
                            address.getAmount(),
                            "COMPLETED",
                            address.getCreatedAt(),
                            address.getExpiresAt()
                        );
                        addressTable.put(updatedAddress);
                        return true;
                    }
                    return false;
                }).join();
        });
    }

    public CompletableFuture<Void> recordTransaction(
            UUID paymentAddressId, String txHash, BigDecimal amount) {
        return explorerClient.getTransactionConfirmations(txHash)
            .thenAccept(confirmations -> {
                PaymentTransaction transaction = new PaymentTransaction(
                    UUID.randomUUID(),
                    paymentAddressId,
                    txHash,
                    amount,
                    confirmations,
                    clock.instant()
                );
                transactionTable.put(transaction);

                if (confirmations >= REQUIRED_CONFIRMATIONS) {
                    // Update payment status to confirmed
                    PaymentAddress address = addressTable.get(paymentAddressId.toString());
                    if (address != null && "PENDING".equals(address.getStatus())) {
                        PaymentAddress updatedAddress = new PaymentAddress(
                            address.getId(),
                            address.getUserId(),
                            address.getCurrency(),
                            address.getAddress(),
                            address.getAmount(),
                            "CONFIRMED",
                            address.getCreatedAt(),
                            address.getExpiresAt()
                        );
                        addressTable.put(updatedAddress);
                    }
                }
            });
    }
}
