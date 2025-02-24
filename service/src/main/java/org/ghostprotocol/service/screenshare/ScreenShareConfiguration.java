package org.ghostprotocol.service.screenshare;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScreenShareConfiguration {
    @JsonProperty
    private int maxResolutionHeight = 720; // Max 720p as per WhatsApp spec

    @JsonProperty
    private int minResolutionHeight = 480; // Min 480p for low bandwidth

    @JsonProperty
    private int maxFrameRate = 30;

    @JsonProperty
    private int minFrameRate = 15;

    @JsonProperty
    private int maxBitrateMbps = 2; // 2 Mbps max bandwidth

    @JsonProperty
    private int minBitrateMbps = 1; // 1 Mbps min for stable sharing

    public int getMaxResolutionHeight() {
        return maxResolutionHeight;
    }

    public int getMinResolutionHeight() {
        return minResolutionHeight;
    }

    public int getMaxFrameRate() {
        return maxFrameRate;
    }

    public int getMinFrameRate() {
        return minFrameRate;
    }

    public int getMaxBitrateMbps() {
        return maxBitrateMbps;
    }

    public int getMinBitrateMbps() {
        return minBitrateMbps;
    }
}
