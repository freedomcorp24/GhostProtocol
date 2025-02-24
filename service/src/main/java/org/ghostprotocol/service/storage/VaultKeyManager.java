package org.ghostprotocol.service.storage;

import org.ghostprotocol.service.util.HmacUtils;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class VaultKeyManager {
    private final HmacUtils hmacUtils;
    private final SecureRandom secureRandom;
    private final ConcurrentHashMap<UUID, byte[]> masterKeys;

    public VaultKeyManager(HmacUtils hmacUtils) {
        this.hmacUtils = hmacUtils;
        this.secureRandom = new SecureRandom();
        this.masterKeys = new ConcurrentHashMap<>();
    }

    public byte[] generateMasterKey(UUID accountId) {
        byte[] masterKey = new byte[32];
        secureRandom.nextBytes(masterKey);
        masterKeys.put(accountId, masterKey);
        return masterKey;
    }

    public byte[] getMasterKey(UUID accountId) {
        return masterKeys.get(accountId);
    }

    public byte[] deriveItemKey(UUID accountId, String itemId) {
        byte[] masterKey = getMasterKey(accountId);
        if (masterKey == null) {
            throw new IllegalStateException("No master key found for account");
        }
        
        // Derive a unique key for each item using HMAC
        String keyMaterial = accountId.toString() + ":" + itemId;
        return hmacUtils.calculateHmacBytes(keyMaterial, masterKey);
    }

    public String generateSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public void removeMasterKey(UUID accountId) {
        masterKeys.remove(accountId);
    }
}
