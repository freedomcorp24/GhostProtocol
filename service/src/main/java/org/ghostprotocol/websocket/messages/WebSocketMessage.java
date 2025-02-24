package org.ghostprotocol.websocket.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Represents a WebSocket message for communication between clients.
 * Used for screen sharing and other real-time features.
 */
public class WebSocketMessage {
    
    @JsonProperty
    private String type;
    
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
    private int bitrate;
    
    @JsonProperty
    private double bandwidth;
    
    @JsonProperty
    private int frameRate;
    
    @JsonProperty
    private String resolution;
    
    @JsonProperty
    private String quality;
    
    @JsonProperty
    private String videoCodec;
    
    @JsonProperty
    private boolean permissionGranted;
    
    @JsonProperty
    private String networkType;
    
    @JsonProperty
    private Map<String, Object> constraints;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
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
    
    public int getBitrate() {
        return bitrate;
    }
    
    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }
    
    public double getBandwidth() {
        return bandwidth;
    }
    
    public void setBandwidth(double bandwidth) {
        this.bandwidth = bandwidth;
    }
    
    public int getFrameRate() {
        return frameRate;
    }
    
    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
    
    public String getResolution() {
        return resolution;
    }
    
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    
    public String getQuality() {
        return quality;
    }
    
    public void setQuality(String quality) {
        this.quality = quality;
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
    
    public Map<String, Object> getConstraints() {
        return constraints;
    }
    
    public void setConstraints(Map<String, Object> constraints) {
        this.constraints = constraints;
    }
}
