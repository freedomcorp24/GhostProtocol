package org.ghostprotocol.service.security.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QRCodeService {
    private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);
    private static final int QR_CODE_SIZE = 300;
    
    private final Map<String, String> qrCodeCache = new HashMap<>();
    private final SecureRandom secureRandom = new SecureRandom();
    
    public String generateQRCode(Account account) {
        try {
            String ghostId = account.getUuid().toString();
            String token = generateToken();
            String qrContent = String.format("ghost://%s?token=%s", ghostId, token);
            
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            
            String qrCode = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            qrCodeCache.put(ghostId, token);
            
            return qrCode;
        } catch (WriterException e) {
            logger.error("Error generating QR code", e);
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
    
    public void regenerateQRCode(Account account) {
        String ghostId = account.getUuid().toString();
        qrCodeCache.remove(ghostId);
        generateQRCode(account);
    }
    
    public boolean verifyQRCode(String ghostId, String token) {
        String cachedToken = qrCodeCache.get(ghostId);
        return cachedToken != null && cachedToken.equals(token);
    }
    
    private String generateToken() {
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
