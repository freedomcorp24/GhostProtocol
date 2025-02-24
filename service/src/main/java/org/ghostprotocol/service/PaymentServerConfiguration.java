package org.ghostprotocol.service.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;

public class PaymentServerConfiguration {
  @JsonProperty
  @NotEmpty
  private String btcNodeUrl;

  @JsonProperty
  @NotEmpty
  private String btcRpcUser;

  @JsonProperty
  @NotEmpty
  private String btcRpcPassword;

  @JsonProperty
  @NotEmpty
  private String xmrNodeUrl;

  @JsonProperty
  @NotEmpty
  private String xmrRpcUser;

  @JsonProperty
  @NotEmpty
  private String xmrRpcPassword;

  @JsonProperty
  @NotEmpty
  private String ethNodeUrl; // For USDT

  @JsonProperty
  @NotEmpty
  private String usdtContractAddress;

  @JsonProperty
  @NotNull
  private HotWalletConfiguration hotWallet;

  @JsonProperty
  @NotNull
  private ColdWalletConfiguration coldWallet;

  @JsonProperty
  @NotNull
  @Valid
  private CryptoNodeConfiguration cryptoNode;

  public static class HotWalletConfiguration {
    @JsonProperty
    @NotEmpty
    private String btcAddress;

    @JsonProperty
    @NotEmpty
    private String xmrAddress;

    @JsonProperty
    @NotEmpty
    private String ethAddress;

    @JsonProperty
    @Min(0)
    private long maxBalanceBtc = 10;

    @JsonProperty
    @Min(0)
    private long maxBalanceXmr = 100;

    @JsonProperty
    @Min(0)
    private long maxBalanceUsdt = 10000;

    public String getBtcAddress() { return btcAddress; }
    public String getXmrAddress() { return xmrAddress; }
    public String getEthAddress() { return ethAddress; }
    public long getMaxBalanceBtc() { return maxBalanceBtc; }
    public long getMaxBalanceXmr() { return maxBalanceXmr; }
    public long getMaxBalanceUsdt() { return maxBalanceUsdt; }
  }

  public static class ColdWalletConfiguration {
    @JsonProperty
    @NotEmpty
    private String btcAddress;

    @JsonProperty
    @NotEmpty
    private String xmrAddress;

    @JsonProperty
    @NotEmpty
    private String ethAddress;

    @JsonProperty
    @Min(0)
    private long sweepThresholdBtc = 5;

    @JsonProperty
    @Min(0)
    private long sweepThresholdXmr = 50;

    @JsonProperty
    @Min(0)
    private long sweepThresholdUsdt = 5000;

    public String getBtcAddress() { return btcAddress; }
    public String getXmrAddress() { return xmrAddress; }
    public String getEthAddress() { return ethAddress; }
    public long getSweepThresholdBtc() { return sweepThresholdBtc; }
    public long getSweepThresholdXmr() { return sweepThresholdXmr; }
    public long getSweepThresholdUsdt() { return sweepThresholdUsdt; }
  }

  public String getBtcNodeUrl() { return btcNodeUrl; }
  public String getBtcRpcUser() { return btcRpcUser; }
  public String getBtcRpcPassword() { return btcRpcPassword; }
  public String getXmrNodeUrl() { return xmrNodeUrl; }
  public String getXmrRpcUser() { return xmrRpcUser; }
  public String getXmrRpcPassword() { return xmrRpcPassword; }
  public String getEthNodeUrl() { return ethNodeUrl; }
  public String getUsdtContractAddress() { return usdtContractAddress; }
  public HotWalletConfiguration getHotWallet() { return hotWallet; }
  public ColdWalletConfiguration getColdWallet() { return coldWallet; }
  public CryptoNodeConfiguration getCryptoNode() { return cryptoNode; }

  public static class CryptoNodeConfiguration {
    @JsonProperty
    @Min(1)
    private int minConfirmations = 3;

    @JsonProperty
    @NotNull
    private Duration paymentTimeout = Duration.ofHours(2);

    @JsonProperty
    @Min(1)
    private int maxRetries = 3;

    @JsonProperty
    @NotNull
    private Duration retryDelay = Duration.ofMinutes(5);

    public int getMinConfirmations() { return minConfirmations; }
    public Duration getPaymentTimeout() { return paymentTimeout; }
    public int getMaxRetries() { return maxRetries; }
    public Duration getRetryDelay() { return retryDelay; }
  }
}
