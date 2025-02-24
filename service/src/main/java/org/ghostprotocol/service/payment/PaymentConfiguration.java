package org.ghostprotocol.service.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentConfiguration {
    @JsonProperty
    private String bitcoinNodeUrl;

    @JsonProperty
    private String bitcoinRpcUser;

    @JsonProperty
    private String bitcoinRpcPassword;

    @JsonProperty
    private String moneroNodeUrl;

    @JsonProperty
    private String moneroWalletUrl;

    @JsonProperty
    private String moneroRpcUser;

    @JsonProperty
    private String moneroRpcPassword;

    @JsonProperty
    private String usdtNodeUrl;

    @JsonProperty
    private String usdtRpcUser;

    @JsonProperty
    private String usdtRpcPassword;

    public String getBitcoinNodeUrl() {
        return bitcoinNodeUrl;
    }

    public String getBitcoinRpcUser() {
        return bitcoinRpcUser;
    }

    public String getBitcoinRpcPassword() {
        return bitcoinRpcPassword;
    }

    public String getMoneroNodeUrl() {
        return moneroNodeUrl;
    }

    public String getMoneroWalletUrl() {
        return moneroWalletUrl;
    }

    public String getMoneroRpcUser() {
        return moneroRpcUser;
    }

    public String getMoneroRpcPassword() {
        return moneroRpcPassword;
    }

    public String getUsdtNodeUrl() {
        return usdtNodeUrl;
    }

    public String getUsdtRpcUser() {
        return usdtRpcUser;
    }

    public String getUsdtRpcPassword() {
        return usdtRpcPassword;
    }
}
