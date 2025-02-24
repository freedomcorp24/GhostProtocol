package org.ghostprotocol.service.contacts;

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
import org.ghostprotocol.service.security.qr.QRContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class QRCodeService {
    private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);
    private static final int QR_CODE_SIZE = 300;
    private static final long DEFAULT_EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours
    
    private final Map<UUID, String> qrCodeCache = new HashMap<>();
    private final CryptoService cryptoService;
    private final QRContactService qrContactService;
    
    @Inject
    public QRCodeService(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
        this.qrContactService = new QRContactService(cryptoService);
    }

    public CompletableFuture<String> generateQRCode(UUID userId) {
        try {
            // Generate QR code containing user's GHOST ID with expiration
            String ghostId = userId.toString();
            String qrCode = qrContactService.generateContactQR(ghostId, System.currentTimeMillis() + DEFAULT_EXPIRATION_TIME);
            qrCodeCache.put(userId, qrCode);
            return CompletableFuture.completedFuture(qrCode);
        } catch (Exception e) {
            logger.error("Error generating QR code", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Void> regenerateQRCode(UUID userId) {
        try {
            // Invalidate old QR code and generate new one
            qrCodeCache.remove(userId);
            generateQRCode(userId);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            logger.error("Error regenerating QR code", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<UUID> resolveQRCode(String qrCode) {
        try {
            // Validate and resolve QR code to user ID
            QRContactService.ContactQRData data = qrContactService.verifyContactQR(qrCode);
            return CompletableFuture.completedFuture(UUID.fromString(data.getUserId()));
        } catch (Exception e) {
            logger.error("Error resolving QR code", e);
            return CompletableFuture.failedFuture(e);
        }
    }
}
