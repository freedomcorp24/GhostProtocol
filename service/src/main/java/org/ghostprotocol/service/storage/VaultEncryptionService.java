package org.ghostprotocol.service.storage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class VaultEncryptionService {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;

    private final SecureRandom secureRandom;

    public VaultEncryptionService() {
        this.secureRandom = new SecureRandom();
    }

    public String encrypt(String plaintext, byte[] key) throws Exception {
        // Generate a random IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        secureRandom.nextBytes(iv);

        // Create cipher instance and initialize
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        // Encrypt
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

        // Combine IV and ciphertext
        byte[] encrypted = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, encrypted, 0, iv.length);
        System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedData, byte[] key) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(encryptedData);

        // Extract IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(decoded, 0, iv, 0, iv.length);

        // Extract ciphertext
        byte[] ciphertext = new byte[decoded.length - GCM_IV_LENGTH];
        System.arraycopy(decoded, GCM_IV_LENGTH, ciphertext, 0, ciphertext.length);

        // Create cipher instance and initialize
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

        // Decrypt
        byte[] decrypted = cipher.doFinal(ciphertext);
        return new String(decrypted);
    }
}
