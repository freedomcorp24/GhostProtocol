package org.ghostprotocol.service.screenshare;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a WebRTC signaling message for screen sharing.
 */
public class SignalMessage {
    
    public enum SignalType {
        OFFER,
        ANSWER,
        ICE_CANDIDATE,
        START_SCREEN_SHARE,
        STOP_SCREEN_SHARE
    }
    
    @JsonProperty
    private SignalType type;
    
    @JsonProperty
    private String sessionId;
    
    @JsonProperty
    private String sourceUserId;
    
    @JsonProperty
    private String targetUserId;
    
    @JsonProperty
    private String sdp;
    
    @JsonProperty
    private Object iceCandidate;
    
    @JsonProperty
    private int maxBitrate;
    
    @JsonProperty
    private int minBitrate;
    
    @JsonProperty
    private int frameRate;
    
    @JsonProperty
    private String videoCodec;
    
    @JsonProperty
    private boolean permissionGranted;
    
    @JsonProperty
    private String networkType;
    
    public SignalType getType() {
        return type;
    }
    
    public void setType(SignalType type) {
        this.type = type;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getSourceUserId() {
        return sourceUserId;
    }
    
    public void setSourceUserId(String sourceUserId) {
        this.sourceUserId = sourceUserId;
    }
    
    public String getTargetUserId() {
        return targetUserId;
    }
    
    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }
    
    public String getSdp() {
        return sdp;
    }
    
    public void setSdp(String sdp) {
        this.sdp = sdp;
    }
    
    public Object getIceCandidate() {
        return iceCandidate;
    }
    
    public void setIceCandidate(Object iceCandidate) {
        this.iceCandidate = iceCandidate;
    }
    
    public int getMaxBitrate() {
        return maxBitrate;
    }
    
    public void setMaxBitrate(int maxBitrate) {
        this.maxBitrate = maxBitrate;
    }
    
    public int getMinBitrate() {
        return minBitrate;
    }
    
    public void setMinBitrate(int minBitrate) {
        this.minBitrate = minBitrate;
    }
    
    public int getFrameRate() {
        return frameRate;
    }
    
    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
    
    public String getVideoCodec() {
        return videoCodec;
    }
    
    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }
    
    public boolean isPermissionGranted() {
        return permissionGranted;
    }
    
    public void setPermissionGranted(boolean permissionGranted) {
        this.permissionGranted = permissionGranted;
    }
    
    public String getNetworkType() {
        return networkType;
    }
    
    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }
}
