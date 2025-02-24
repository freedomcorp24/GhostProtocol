package org.ghostprotocol.service.auth;

import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;
import org.ghostprotocol.service.util.HmacUtils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

public class TwoFactorAuthenticationService {
    private static final int PATTERN_MIN_LENGTH = 4;
    private static final int PATTERN_MAX_LENGTH = 9;
    private static final int PASSWORD_MIN_LENGTH = 8;

    private final HmacUtils hmacUtils;
    private final SecureRandom secureRandom;

    public TwoFactorAuthenticationService(HmacUtils hmacUtils) {
        this.hmacUtils = hmacUtils;
        this.secureRandom = new SecureRandom();
    }

    public void setPatternAuth(Account account, Device device, String pattern) {
        validatePattern(pattern);
        String salt = generateSalt();
        String hashedPattern = hashCredential(pattern, salt);
        device.setTwoFactorAuthType(TwoFactorAuthType.PATTERN);
        device.setTwoFactorAuthHash(hashedPattern);
        device.setTwoFactorAuthSalt(salt);
    }

    public void setPasswordAuth(Account account, Device device, String password) {
        validatePassword(password);
        String salt = generateSalt();
        String hashedPassword = hashCredential(password, salt);
        device.setTwoFactorAuthType(TwoFactorAuthType.PASSWORD);
        device.setTwoFactorAuthHash(hashedPassword);
        device.setTwoFactorAuthSalt(salt);
    }

    public void setUsernameAuth(Account account, Device device, String username) {
        validateUsername(username);
        String salt = generateSalt();
        String hashedCredential = hashCredential(username, salt);
        device.setTwoFactorAuthType(TwoFactorAuthType.USERNAME);
        device.setTwoFactorAuthHash(hashedCredential);
        device.setTwoFactorAuthSalt(salt);
    }

    public boolean verifyTwoFactorAuth(Device device, String credential) {
        if (device.getTwoFactorAuthType() == null || 
            device.getTwoFactorAuthHash() == null || 
            device.getTwoFactorAuthSalt() == null) {
            return false;
        }

        String hashedCredential = hashCredential(credential, device.getTwoFactorAuthSalt());
        return hmacUtils.verifyHmac(hashedCredential, device.getTwoFactorAuthHash());
    }

    private void validatePattern(String pattern) {
        if (pattern == null || pattern.length() < PATTERN_MIN_LENGTH || pattern.length() > PATTERN_MAX_LENGTH) {
            throw new IllegalArgumentException("Invalid pattern length");
        }
        // Pattern format: comma-separated list of numbers from 0-8 representing grid positions
        if (!pattern.matches("^[0-8](,[0-8]){" + (PATTERN_MIN_LENGTH-1) + "," + (PATTERN_MAX_LENGTH-1) + "}$")) {
            throw new IllegalArgumentException("Invalid pattern format");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < PASSWORD_MIN_LENGTH) {
            throw new IllegalArgumentException("Password too short");
        }
        // Require at least one uppercase, one lowercase, one number
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new IllegalArgumentException("Password must contain uppercase, lowercase, and numbers");
        }
    }
    
    private void validateUsername(String username) {
        if (username == null || username.length() < 3 || username.length() > 32) {
            throw new IllegalArgumentException("Invalid username length");
        }
        // Username format: alphanumeric with underscore and hyphen
        if (!username.matches("^[a-zA-Z0-9_-]{3,32}$")) {
            throw new IllegalArgumentException("Invalid username format");
        }
    }

    private String generateSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashCredential(String credential, String salt) {
        return hmacUtils.calculateHmac(credential + salt);
    }

    public enum TwoFactorAuthType {
        PATTERN,
        PASSWORD,
        USERNAME
    }
}
