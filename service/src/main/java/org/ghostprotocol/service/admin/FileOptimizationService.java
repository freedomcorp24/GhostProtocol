package org.ghostprotocol.service.admin;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImagingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileOptimizationService {
    private static final Logger logger = LoggerFactory.getLogger(FileOptimizationService.class);
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    private static final int MAX_IMAGE_DIMENSION = 2048;
    private static final int JPEG_QUALITY = 85;
    private static final int MAX_VIDEO_BITRATE = 2500000; // 2.5 Mbps

    public byte[] optimizeImage(byte[] imageData, String mimeType) {
        try {
            // Use Thumbnailator for resizing and compression
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
            if (originalImage == null) {
                logger.error("Failed to read image data");
                return imageData;
            }

            // Calculate new dimensions while maintaining aspect ratio
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            if (width > MAX_IMAGE_DIMENSION || height > MAX_IMAGE_DIMENSION) {
                double scale = Math.min((double) MAX_IMAGE_DIMENSION / width, (double) MAX_IMAGE_DIMENSION / height);
                width = (int) (width * scale);
                height = (int) (height * scale);
            }

            // Create optimized image using Thumbnailator
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(originalImage)
                    .size(width, height)
                    .outputQuality((double) JPEG_QUALITY / 100)
                    .outputFormat(mimeType.substring(mimeType.lastIndexOf("/") + 1))
                    .toOutputStream(outputStream);

            // Strip metadata using Apache Commons Imaging
            byte[] optimizedData = outputStream.toByteArray();
            ImageFormat format = ImageFormats.valueOf(mimeType.toUpperCase().replace("IMAGE/", ""));
            final Map<String, Object> params = new HashMap<>();
            params.put(ImagingConstants.PARAM_KEY_REMOVE_METADATA, Boolean.TRUE);
            
            BufferedImage strippedImage = Imaging.getBufferedImage(new ByteArrayInputStream(optimizedData), params);
            ByteArrayOutputStream finalOutput = new ByteArrayOutputStream();
            Imaging.writeImage(strippedImage, finalOutput, format, params);
            
            return finalOutput.toByteArray();
        } catch (Exception e) {
            logger.error("Error optimizing image", e);
            return imageData;
        }
    }

    public byte[] optimizeVideo(byte[] videoData, String mimeType) {
        try {
            // Create temporary files for input and output
            Path inputPath = Files.createTempFile("input_", "." + mimeType.substring(mimeType.lastIndexOf("/") + 1));
            Path outputPath = Files.createTempFile("output_", ".mp4");
            Files.write(inputPath, videoData);

            // Use FFmpeg for video optimization
            ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-i", inputPath.toString(),
                "-c:v", "libx264",
                "-preset", "medium",
                "-b:v", MAX_VIDEO_BITRATE + "",
                "-c:a", "aac",
                "-b:a", "128k",
                "-movflags", "+faststart",
                outputPath.toString()
            );

            Process process = pb.start();
            if (!process.waitFor(5, TimeUnit.MINUTES)) {
                process.destroy();
                throw new RuntimeException("Video optimization timed out");
            }

            // Read optimized video
            byte[] optimizedData = Files.readAllBytes(outputPath);

            // Cleanup temporary files
            Files.deleteIfExists(inputPath);
            Files.deleteIfExists(outputPath);

            return optimizedData;
        } catch (Exception e) {
            logger.error("Error optimizing video", e);
            return videoData;
        }
    }

    public void scheduleOptimization(String fileId, byte[] data, String mimeType) {
        executor.submit(() -> {
            try {
                byte[] optimizedData;
                if (mimeType.startsWith("image/")) {
                    optimizedData = optimizeImage(data, mimeType);
                } else if (mimeType.startsWith("video/")) {
                    optimizedData = optimizeVideo(data, mimeType);
                } else {
                    return;
                }
                storeOptimizedFile(fileId, optimizedData);
            } catch (Exception e) {
                logger.error("Error in optimization task", e);
            }
        });
    }

    private void storeOptimizedFile(String fileId, byte[] data) {
        // Implementation for storing optimized file
        logger.info("Optimized file stored: {}", fileId);
    }
}
