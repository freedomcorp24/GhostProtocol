package org.ghostprotocol.service.security.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.ghostprotocol.service.crypto.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
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
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300);
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            logger.error("Error generating QR code", e);
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }

    private String decodeQRCode(String qrCode) {
        try {
            byte[] imageData = Base64.getDecoder().decode(qrCode);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
            
            BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                    new BufferedImageLuminanceSource(image)
                )
            );
            
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (Exception e) {
            logger.error("Error decoding QR code", e);
            throw new RuntimeException("Failed to decode QR code", e);
        }
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

        public String getUserId() {
            return userId;
        }

        public String getNonce() {
            return nonce;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public long getExpirationTime() {
            return expirationTime;
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
