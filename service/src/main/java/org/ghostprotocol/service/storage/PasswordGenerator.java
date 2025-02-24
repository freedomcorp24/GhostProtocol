package org.ghostprotocol.service.storage;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    
    private final SecureRandom random;

    public PasswordGenerator() {
        this.random = new SecureRandom();
    }

    public String generatePassword(int length, boolean includeUpper, boolean includeLower, 
                                 boolean includeDigits, boolean includeSpecial) {
        StringBuilder charset = new StringBuilder();
        if (includeUpper) charset.append(UPPER);
        if (includeLower) charset.append(LOWER);
        if (includeDigits) charset.append(DIGITS);
        if (includeSpecial) charset.append(SPECIAL);

        if (charset.length() == 0) {
            throw new IllegalArgumentException("At least one character set must be selected");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charset.length());
            password.append(charset.charAt(randomIndex));
        }

        return password.toString();
    }
}
