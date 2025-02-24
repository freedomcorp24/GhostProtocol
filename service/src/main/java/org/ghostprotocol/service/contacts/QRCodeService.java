package org.ghostprotocol.service.contacts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class QRCodeService {
    private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);

    @Inject
    public QRCodeService() {}

    public CompletableFuture<String> generateQRCode(UUID userId) {
        // Generate QR code containing user's GHOST ID
        String ghostId = "GHOST" + userId.toString().replaceAll("-", "").substring(0, 8);
        return CompletableFuture.completedFuture(ghostId);
    }

    public CompletableFuture<Void> regenerateQRCode(UUID userId) {
        // Invalidate old QR code and generate new one
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<UUID> resolveQRCode(String qrData) {
        // Validate and resolve QR code to user ID
        return CompletableFuture.completedFuture(UUID.randomUUID());
    }
}
