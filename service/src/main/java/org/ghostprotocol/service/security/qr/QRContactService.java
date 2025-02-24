package org.ghostprotocol.service.security.qr;

import org.ghostprotocol.service.crypto.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class QRContactService {
    private static final Logger logger = LoggerFactory.getLogger(QRContactService.class);
    private final CryptoService cryptoService;

    public QRContactService(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    public String generateContactQR(String userId, long expirationTime) {
        ContactQRData data = new ContactQRData(
            userId,
            UUID.randomUUID().toString(),
            System.currentTimeMillis(),
            expirationTime
        );
        
        String encryptedData = cryptoService.encrypt(data.toString());
        return generateQRCode(encryptedData);
    }

    public ContactQRData verifyContactQR(String qrCode) {
        String encryptedData = decodeQRCode(qrCode);
        String decryptedData = cryptoService.decrypt(encryptedData);
        ContactQRData data = ContactQRData.fromString(decryptedData);
        
        if (System.currentTimeMillis() > data.expirationTime) {
            throw new IllegalStateException("QR code has expired");
        }
        
        return data;
    }

    private String generateQRCode(String data) {
        // Implementation for QR code generation
        return "qr_code_data";
    }

    private String decodeQRCode(String qrCode) {
        // Implementation for QR code decoding
        return "decoded_data";
    }

    public static class ContactQRData {
        private final String userId;
        private final String nonce;
        private final long timestamp;
        private final long expirationTime;

        public ContactQRData(String userId, String nonce, long timestamp, long expirationTime) {
            this.userId = userId;
            this.nonce = nonce;
            this.timestamp = timestamp;
            this.expirationTime = expirationTime;
        }

        @Override
        public String toString() {
            return String.format("%s:%s:%d:%d", userId, nonce, timestamp, expirationTime);
        }

        public static ContactQRData fromString(String str) {
            String[] parts = str.split(":");
            return new ContactQRData(
                parts[0],
                parts[1],
                Long.parseLong(parts[2]),
                Long.parseLong(parts[3])
            );
        }
    }
}
