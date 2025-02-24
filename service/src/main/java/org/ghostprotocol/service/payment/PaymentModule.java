package org.ghostprotocol.service.payment;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.ghostprotocol.service.http.FaultTolerantHttpClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PaymentModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bind implementations
    }

    @Provides
    @Singleton
    public CoinGeckoClient provideCoinGeckoClient(FaultTolerantHttpClient httpClient) {
        return new CoinGeckoClient(httpClient);
    }

    @Provides
    @Singleton
    public ExchangeRateManager provideExchangeRateManager(CoinGeckoClient coinGeckoClient) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        return new ExchangeRateManager(coinGeckoClient, scheduler);
    }

    @Provides
    @Singleton
    public BitcoinClient provideBitcoinClient(
            FaultTolerantHttpClient httpClient,
            PaymentConfiguration config) {
        return new BitcoinClient(
            httpClient,
            config.getBitcoinNodeUrl(),
            config.getBitcoinRpcUser(),
            config.getBitcoinRpcPassword()
        );
    }

    @Provides
    @Singleton
    public MoneroClient provideMoneroClient(
            FaultTolerantHttpClient httpClient,
            PaymentConfiguration config) {
        return new MoneroClient(
            httpClient,
            config.getMoneroNodeUrl(),
            config.getMoneroWalletUrl(),
            config.getMoneroRpcUser(),
            config.getMoneroRpcPassword()
        );
    }

    @Provides
    @Singleton
    public UsdtClient provideUsdtClient(
            FaultTolerantHttpClient httpClient,
            PaymentConfiguration config) {
        return new UsdtClient(
            httpClient,
            config.getUsdtNodeUrl(),
            config.getUsdtRpcUser(),
            config.getUsdtRpcPassword()
        );
    }

    @Provides
    @Singleton
    public HDWalletManager provideHDWalletManager(
            BitcoinClient bitcoinClient,
            MoneroClient moneroClient,
            UsdtClient usdtClient) {
        return new HDWalletManager(bitcoinClient, moneroClient, usdtClient);
    }

    @Provides
    @Singleton
    public CryptoPaymentService provideCryptoPaymentService(
            BlockExplorerClient blockExplorerClient,
            HDWalletManager hdWalletManager,
            ExchangeRateManager exchangeRateManager) {
        return new CryptoPaymentService(blockExplorerClient, hdWalletManager, exchangeRateManager);
    }

    @Provides
    @Singleton
    public CryptoWalletService provideCryptoWalletService(
            HDWalletManager hdWalletManager,
            BlockExplorerClient blockExplorerClient,
            CryptoPaymentService paymentService) {
        return new CryptoWalletService(hdWalletManager, blockExplorerClient, paymentService);
    }

    @Provides
    @Singleton
    public CryptoTransactionManager provideCryptoTransactionManager(
            CryptoWalletService walletService,
            CryptoPaymentService paymentService) {
        return new CryptoTransactionManager(walletService, paymentService);
    }
}
