package org.ghostprotocol.service.security;

import org.ghostprotocol.service.crypto.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class TwoFactorAuthService {
    private static final Logger logger = LoggerFactory.getLogger(TwoFactorAuthService.class);
    private static final String HMAC_ALGO = "HmacSHA256";
    private static final int CODE_LENGTH = 6;
    private static final int CODE_VALIDITY_SECONDS = 30;
    
    private final ConcurrentHashMap<String, String> userSecrets = new ConcurrentHashMap<>();
    private final CryptoService cryptoService;

    public TwoFactorAuthService(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    public String generateSecret(String userId) {
        byte[] secret = new byte[32];
        new SecureRandom().nextBytes(secret);
        String secretKey = Base64.getEncoder().encodeToString(secret);
        userSecrets.put(userId, cryptoService.encrypt(secretKey));
        return secretKey;
    }

    public boolean verifyCode(String userId, String code) {
        String encryptedSecret = userSecrets.get(userId);
        if (encryptedSecret == null) {
            return false;
        }

        String secret = cryptoService.decrypt(encryptedSecret);
        long timeWindow = System.currentTimeMillis() / 1000 / CODE_VALIDITY_SECONDS;
        
        // Check current and previous time windows
        return validateCode(secret, code, timeWindow) || 
               validateCode(secret, code, timeWindow - 1);
    }

    private boolean validateCode(String secret, String code, long timeWindow) {
        try {
            byte[] secretBytes = Base64.getDecoder().decode(secret);
            byte[] timeBytes = longToBytes(timeWindow);
            
            Mac mac = Mac.getInstance(HMAC_ALGO);
            mac.init(new SecretKeySpec(secretBytes, HMAC_ALGO));
            
            byte[] hash = mac.doFinal(timeBytes);
            int offset = hash[hash.length - 1] & 0xf;
            int truncatedHash = ((hash[offset] & 0x7f) << 24) |
                              ((hash[offset + 1] & 0xff) << 16) |
                              ((hash[offset + 2] & 0xff) << 8) |
                              (hash[offset + 3] & 0xff);
            
            int validCode = truncatedHash % (int) Math.pow(10, CODE_LENGTH);
            return String.format("%0" + CODE_LENGTH + "d", validCode).equals(code);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("Error validating 2FA code", e);
            return false;
        }
    }

    private byte[] longToBytes(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return result;
    }
}
