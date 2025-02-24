package org.ghostprotocol.service.auth;

import org.ghostprotocol.service.security.TwoFactorAuthService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;
import org.ghostprotocol.service.util.HmacUtils;

import java.util.Optional;

public class UnifiedTwoFactorAuthService {
    private final TwoFactorAuthenticationService patternPasswordAuth;
    private final TwoFactorAuthService totpAuth;
    private final BiometricAuthenticationService biometricAuth;

    public UnifiedTwoFactorAuthService(
            TwoFactorAuthenticationService patternPasswordAuth,
            TwoFactorAuthService totpAuth,
            BiometricAuthenticationService biometricAuth) {
        this.patternPasswordAuth = patternPasswordAuth;
        this.totpAuth = totpAuth;
        this.biometricAuth = biometricAuth;
    }

    public void setupTOTP(Account account) {
        String secret = totpAuth.generateSecret(account.getUuid());
        account.setTotpSecret(secret);
        account.setTwoFactorAuthEnabled(true);
        account.setTwoFactorAuthType(TwoFactorAuthType.TOTP);
    }

    public void setupPattern(Account account, Device device, String pattern) {
        patternPasswordAuth.setPatternAuth(account, device, pattern);
        account.setTwoFactorAuthEnabled(true);
        account.setTwoFactorAuthType(TwoFactorAuthType.PATTERN);
    }

    public void setupPassword(Account account, Device device, String password) {
        patternPasswordAuth.setPasswordAuth(account, device, password);
        account.setTwoFactorAuthEnabled(true);
        account.setTwoFactorAuthType(TwoFactorAuthType.PASSWORD);
    }

    public void setupBiometric(Account account, Device device, String biometricPublicKey) {
        biometricAuth.enableBiometricAuth(account, device, biometricPublicKey);
        account.setTwoFactorAuthEnabled(true);
        account.setTwoFactorAuthType(TwoFactorAuthType.BIOMETRIC);
    }
    
    public void setupUsername(Account account, Device device, String username) {
        patternPasswordAuth.setUsernameAuth(account, device, username);
        account.setTwoFactorAuthEnabled(true);
        account.setTwoFactorAuthType(TwoFactorAuthType.USERNAME);
    }

    public boolean verify(Account account, Device device, String credential) {
        if (!account.isTwoFactorAuthEnabled()) {
            return false;
        }

        switch (account.getTwoFactorAuthType()) {
            case TOTP:
                return totpAuth.verifyCode(account.getUuid(), credential);
            case PATTERN:
            case PASSWORD:
                return patternPasswordAuth.verifyTwoFactorAuth(device, credential);
            case BIOMETRIC:
                return biometricAuth.verifyBiometricAuth(account, device, credential);
            default:
                return false;
        }
    }

    public void disable(Account account, Device device) {
        account.setTwoFactorAuthEnabled(false);
        account.setTwoFactorAuthType(null);
        account.setTotpSecret(null);
        
        if (biometricAuth.isBiometricAuthEnabled(account, device)) {
            biometricAuth.disableBiometricAuth(account, device);
        }
    }

    public enum TwoFactorAuthType {
        TOTP,
        PATTERN,
        PASSWORD,
        BIOMETRIC,
        USERNAME
    }
}
