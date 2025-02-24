package org.ghostprotocol.service.screenshare;

/**
 * Configuration for screen sharing based on WhatsApp specifications.
 * Supports adaptive resolution and bandwidth management.
 */
public class ScreenShareConfig {
    // Default bitrate values based on WhatsApp specs
    private static final int DEFAULT_MIN_BITRATE = 500000; // 500 Kbps
    private static final int DEFAULT_MAX_BITRATE = 2500000; // 2.5 Mbps
    private static final int DEFAULT_TARGET_BITRATE = 1500000; // 1.5 Mbps
    
    // Default frame rate values
    private static final int MIN_FRAME_RATE = 15;
    private static final int MAX_FRAME_RATE = 30;
    private static final int DEFAULT_FRAME_RATE = 30;
    
    // Default codec settings
    private static final String DEFAULT_VIDEO_CODEC = "VP8";
    private static final String[] SUPPORTED_CODECS = {"VP8", "VP9", "H.264"};
    
    private int minBitrate = DEFAULT_MIN_BITRATE;
    private int maxBitrate = DEFAULT_MAX_BITRATE;
    private int targetBitrate = DEFAULT_TARGET_BITRATE;
    private int minFrameRate = MIN_FRAME_RATE;
    private int maxFrameRate = MAX_FRAME_RATE;
    private int frameRate = DEFAULT_FRAME_RATE;
    private Resolution resolution = Resolution.P720;
    private String videoCodec = DEFAULT_VIDEO_CODEC;
    private boolean adaptiveResolution = true;
    private boolean adaptiveFrameRate = true;
    private boolean adaptiveBitrate = true;
    private int dataUsageLimit = 0; // 0 means no limit, value in MB per hour
    
    // Network type specific settings
    private int wifiMaxBitrate = DEFAULT_MAX_BITRATE;
    private int cellularMaxBitrate = 1500000; // 1.5 Mbps for cellular

    public int getMinBitrate() {
        return minBitrate;
    }

    public void setMinBitrate(int minBitrate) {
        this.minBitrate = minBitrate;
    }

    public int getMaxBitrate() {
        return maxBitrate;
    }

    public void setMaxBitrate(int maxBitrate) {
        this.maxBitrate = maxBitrate;
    }
    
    public int getTargetBitrate() {
        return targetBitrate;
    }
    
    public void setTargetBitrate(int targetBitrate) {
        this.targetBitrate = targetBitrate;
    }

    public int getMinFrameRate() {
        return minFrameRate;
    }
    
    public void setMinFrameRate(int minFrameRate) {
        this.minFrameRate = minFrameRate;
    }
    
    public int getMaxFrameRate() {
        return maxFrameRate;
    }
    
    public void setMaxFrameRate(int maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
    
    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        if (frameRate < minFrameRate) {
            this.frameRate = minFrameRate;
        } else if (frameRate > maxFrameRate) {
            this.frameRate = maxFrameRate;
        } else {
            this.frameRate = frameRate;
        }
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setInitialResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public void setInitialFrameRate(int frameRate) {
        setFrameRate(frameRate);
    }
    
    public String getVideoCodec() {
        return videoCodec;
    }
    
    public void setVideoCodec(String codec) {
        for (String supportedCodec : SUPPORTED_CODECS) {
            if (supportedCodec.equalsIgnoreCase(codec)) {
                this.videoCodec = supportedCodec;
                return;
            }
        }
        // If codec not supported, keep default
    }
    
    public boolean isAdaptiveResolution() {
        return adaptiveResolution;
    }
    
    public void setAdaptiveResolution(boolean adaptiveResolution) {
        this.adaptiveResolution = adaptiveResolution;
    }
    
    public boolean isAdaptiveFrameRate() {
        return adaptiveFrameRate;
    }
    
    public void setAdaptiveFrameRate(boolean adaptiveFrameRate) {
        this.adaptiveFrameRate = adaptiveFrameRate;
    }
    
    public boolean isAdaptiveBitrate() {
        return adaptiveBitrate;
    }
    
    public void setAdaptiveBitrate(boolean adaptiveBitrate) {
        this.adaptiveBitrate = adaptiveBitrate;
    }
    
    public int getDataUsageLimit() {
        return dataUsageLimit;
    }
    
    public void setDataUsageLimit(int dataUsageLimit) {
        this.dataUsageLimit = dataUsageLimit;
    }
    
    public int getWifiMaxBitrate() {
        return wifiMaxBitrate;
    }
    
    public void setWifiMaxBitrate(int wifiMaxBitrate) {
        this.wifiMaxBitrate = wifiMaxBitrate;
    }
    
    public int getCellularMaxBitrate() {
        return cellularMaxBitrate;
    }
    
    public void setCellularMaxBitrate(int cellularMaxBitrate) {
        this.cellularMaxBitrate = cellularMaxBitrate;
    }
    
    public int getMaxBitrateForNetworkType(String networkType) {
        if ("wifi".equalsIgnoreCase(networkType)) {
            return wifiMaxBitrate;
        } else if ("cellular".equalsIgnoreCase(networkType)) {
            return cellularMaxBitrate;
        }
        return maxBitrate; // Default
    }

    public enum Resolution {
        P480(854, 480),
        P720(1280, 720);

        private final int width;
        private final int height;

        Resolution(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
        
        public static Resolution fromHeight(int height) {
            if (height <= 480) {
                return P480;
            }
            return P720; // Default to 720p for anything higher
        }
    }
}
