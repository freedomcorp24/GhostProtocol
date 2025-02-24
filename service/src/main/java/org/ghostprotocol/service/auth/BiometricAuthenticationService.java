package org.ghostprotocol.service.auth;

import org.ghostprotocol.service.security.BiometricAuthManager;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import java.util.Optional;

public class BiometricAuthenticationService {
    private final BiometricAuthManager biometricAuthManager;

    public BiometricAuthenticationService(BiometricAuthManager biometricAuthManager) {
        this.biometricAuthManager = biometricAuthManager;
    }

    public boolean verifyBiometricAuth(Account account, Device device, String biometricToken) {
        return biometricAuthManager.verifyBiometricToken(account.getUuid(), device.getId(), biometricToken);
    }

    public void enableBiometricAuth(Account account, Device device, String biometricPublicKey) {
        biometricAuthManager.storeBiometricKey(account.getUuid(), device.getId(), biometricPublicKey);
    }

    public void disableBiometricAuth(Account account, Device device) {
        biometricAuthManager.removeBiometricKey(account.getUuid(), device.getId());
    }

    public Optional<String> getBiometricPublicKey(Account account, Device device) {
        return biometricAuthManager.getBiometricKey(account.getUuid(), device.getId());
    }

    public boolean isBiometricAuthEnabled(Account account, Device device) {
        return biometricAuthManager.hasBiometricKey(account.getUuid(), device.getId());
    }
}
