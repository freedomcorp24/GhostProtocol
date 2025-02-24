package org.ghostprotocol.service.screenshare;

public class ScreenShareConfig {
    private static final int DEFAULT_MIN_BITRATE = 500000; // 500 Kbps
    private static final int DEFAULT_MAX_BITRATE = 2500000; // 2.5 Mbps
    
    private int minBitrate = DEFAULT_MIN_BITRATE;
    private int maxBitrate = DEFAULT_MAX_BITRATE;
    private int frameRate = 30;
    private Resolution resolution = Resolution.P720;

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

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setInitialResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public void setInitialFrameRate(int frameRate) {
        this.frameRate = frameRate;
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
    }
}
