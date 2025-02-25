package org.ghostprotocol.service.screenshare;

import org.ghostprotocol.websocket.WebSocketConnection;
import org.ghostprotocol.websocket.messages.WebSocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Manages a screen sharing session between users.
 * Implements adaptive resolution and bandwidth management based on WhatsApp specifications.
 */
public class ScreenShareSession {
    private static final Logger logger = LoggerFactory.getLogger(ScreenShareSession.class);
    private final String sessionId;
    private final WebSocketConnection connection;
    private final ScreenShareConfig config;
    private volatile boolean running = false;
    private ScheduledFuture<?> qualityMonitorTask;
    private ScheduledExecutorService scheduler;
    private final Map<String, Object> webRtcConstraints = new HashMap<>();
    private NetworkQuality currentNetworkQuality = NetworkQuality.GOOD;
    private Resolution currentResolution = Resolution.P720;
    private int currentFrameRate = 30;
    private int currentBitrate = 2000000; // 2 Mbps default

    public ScreenShareSession(String sessionId, WebSocketConnection connection, ScreenShareConfig config) {
        this.sessionId = sessionId;
        this.connection = connection;
        this.config = config;
        this.scheduler = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        running = true;
        // Initialize WebRTC connection and start monitoring
        initializeWebRTC();
        startQualityMonitoring();
        logger.info("Screen sharing session started: {}", sessionId);
    }

    public void handleMessage(WebSocketMessage message) {
        if (!running) return;

        switch (message.getType()) {
            case "quality":
                handleQualityUpdate(message);
                break;
            case "bandwidth":
                handleBandwidthUpdate(message);
                break;
            case "ice":
                handleICECandidate(message);
                break;
            case "network_change":
                handleNetworkChange(message);
                break;
            case "permission":
                handlePermissionResponse(message);
                break;
            default:
                logger.warn("Unknown message type: {}", message.getType());
        }
    }

    private void handleQualityUpdate(WebSocketMessage message) {
        // Implement adaptive quality based on client feedback
        int reportedBitrate = message.getBitrate();
        logger.debug("Quality update received: {} bps", reportedBitrate);
        
        if (reportedBitrate < config.getMinBitrate()) {
            adjustQuality(Quality.LOW);
        } else if (reportedBitrate < config.getMaxBitrate() * 0.5) {
            adjustQuality(Quality.MEDIUM);
        } else {
            adjustQuality(Quality.HIGH);
        }
        
        // Send quality adjustment notification to client
        WebSocketMessage response = new WebSocketMessage();
        response.setType("quality_adjusted");
        response.setQuality(currentQualityAsString());
        response.setBitrate(currentBitrate);
        connection.send(response);
    }

    private void handleBandwidthUpdate(WebSocketMessage message) {
        // Implement bandwidth adaptation based on WhatsApp specs
        double bandwidth = message.getBandwidth();
        logger.debug("Bandwidth update received: {} Mbps", bandwidth);
        
        if (bandwidth < 1.0) { // Less than 1 Mbps
            adjustResolution(Resolution.P480);
            currentFrameRate = 15;
            currentBitrate = 500000; // 500 Kbps
            currentNetworkQuality = NetworkQuality.POOR;
        } else if (bandwidth < 2.0) { // 1-2 Mbps
            adjustResolution(Resolution.P480);
            currentFrameRate = 20;
            currentBitrate = 1000000; // 1 Mbps
            currentNetworkQuality = NetworkQuality.MODERATE;
        } else { // 2+ Mbps
            adjustResolution(Resolution.P720);
            currentFrameRate = 30;
            currentBitrate = 2000000; // 2 Mbps
            currentNetworkQuality = NetworkQuality.GOOD;
        }
        
        updateWebRtcConstraints();
        
        // Notify client of bandwidth adaptation
        WebSocketMessage response = new WebSocketMessage();
        response.setType("bandwidth_adapted");
        response.setResolution(currentResolution.toString());
        response.setFrameRate(currentFrameRate);
        response.setBitrate(currentBitrate);
        connection.send(response);
    }

    private void handleICECandidate(WebSocketMessage message) {
        // Handle WebRTC ICE candidates
        logger.debug("ICE candidate received for session: {}", sessionId);
        connection.send(message);
    }
    
    private void handleNetworkChange(WebSocketMessage message) {
        // Handle network type changes (WiFi/Cellular)
        String networkType = message.getNetworkType();
        logger.info("Network changed to {} for session: {}", networkType, sessionId);
        
        // Adjust quality based on network type
        if ("wifi".equalsIgnoreCase(networkType)) {
            // WiFi typically allows higher quality
            config.setMaxBitrate(2500000); // 2.5 Mbps
        } else if ("cellular".equalsIgnoreCase(networkType)) {
            // Cellular networks may need more conservative settings
            config.setMaxBitrate(1500000); // 1.5 Mbps
        }
        
        // Trigger bandwidth check
        WebSocketMessage bandwidthCheck = new WebSocketMessage();
        bandwidthCheck.setType("check_bandwidth");
        connection.send(bandwidthCheck);
    }
    
    private void handlePermissionResponse(WebSocketMessage message) {
        boolean permissionGranted = message.isPermissionGranted();
        logger.info("Screen sharing permission response: {} for session: {}", 
                    permissionGranted ? "granted" : "denied", sessionId);
        
        if (!permissionGranted) {
            // Handle permission denial
            stop();
            
            // Notify user that permission was denied
            WebSocketMessage response = new WebSocketMessage();
            response.setType("permission_denied");
            connection.send(response);
        }
    }

    private void initializeWebRTC() {
        // Initialize WebRTC with default 720p resolution per WhatsApp specs
        currentResolution = Resolution.P720;
        currentFrameRate = 30;
        currentBitrate = 2000000; // 2 Mbps default
        
        // Set up WebRTC constraints
        webRtcConstraints.put("audio", false); // Screen share doesn't need audio
        webRtcConstraints.put("video", createVideoConstraints());
        
        // Request screen sharing permission from user
        WebSocketMessage permissionRequest = new WebSocketMessage();
        permissionRequest.setType("request_permission");
        connection.send(permissionRequest);
        
        logger.info("WebRTC initialized for session: {} with resolution: {}, framerate: {}, bitrate: {} bps", 
                   sessionId, currentResolution, currentFrameRate, currentBitrate);
    }
    
    private Map<String, Object> createVideoConstraints() {
        Map<String, Object> videoConstraints = new HashMap<>();
        
        // Set resolution constraints
        Map<String, Integer> resolution = new HashMap<>();
        resolution.put("width", currentResolution.getWidth());
        resolution.put("height", currentResolution.getHeight());
        videoConstraints.put("resolution", resolution);
        
        // Set framerate constraint
        videoConstraints.put("frameRate", currentFrameRate);
        
        // Set bitrate constraint
        videoConstraints.put("bitrate", currentBitrate);
        
        return videoConstraints;
    }

    private void adjustQuality(Quality quality) {
        logger.debug("Adjusting quality to: {} for session: {}", quality, sessionId);
        
        switch (quality) {
            case LOW:
                currentBitrate = config.getMinBitrate();
                currentFrameRate = 15;
                break;
            case MEDIUM:
                currentBitrate = (config.getMinBitrate() + config.getMaxBitrate()) / 2;
                currentFrameRate = 20;
                break;
            case HIGH:
                currentBitrate = config.getMaxBitrate();
                currentFrameRate = 30;
                break;
        }
        
        updateWebRtcConstraints();
    }

    private void adjustResolution(Resolution resolution) {
        if (currentResolution == resolution) {
            return; // No change needed
        }
        
        logger.debug("Adjusting resolution to: {} for session: {}", resolution, sessionId);
        currentResolution = resolution;
        updateWebRtcConstraints();
    }
    
    private void updateWebRtcConstraints() {
        webRtcConstraints.put("video", createVideoConstraints());
        
        // Send updated constraints to client
        WebSocketMessage constraintsUpdate = new WebSocketMessage();
        constraintsUpdate.setType("constraints_update");
        constraintsUpdate.setConstraints(webRtcConstraints);
        connection.send(constraintsUpdate);
    }
    
    private void startQualityMonitoring() {
        // Periodically check quality and adjust as needed
        if (scheduler != null) {
            qualityMonitorTask = scheduler.scheduleAtFixedRate(() -> {
                if (running) {
                    // Request quality metrics from client
                    WebSocketMessage qualityCheck = new WebSocketMessage();
                    qualityCheck.setType("check_quality");
                    connection.send(qualityCheck);
                }
            }, 5, 30, TimeUnit.SECONDS); // Check every 30 seconds
        }
    }
    
    private String currentQualityAsString() {
        if (currentBitrate <= config.getMinBitrate()) {
            return "low";
        } else if (currentBitrate >= config.getMaxBitrate()) {
            return "high";
        } else {
            return "medium";
        }
    }

    public void stop() {
        if (!running) {
            return; // Already stopped
        }
        
        running = false;
        
        // Cancel quality monitoring
        if (qualityMonitorTask != null) {
            qualityMonitorTask.cancel(false);
            qualityMonitorTask = null;
        }
        
        // Send stop message to client
        WebSocketMessage stopMessage = new WebSocketMessage();
        stopMessage.setType("stop_sharing");
        connection.send(stopMessage);
        
        logger.info("Screen sharing session stopped: {}", sessionId);
    }

    private enum Quality {
        LOW, MEDIUM, HIGH
    }
    
    private enum NetworkQuality {
        POOR, MODERATE, GOOD
    }

    private enum Resolution {
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
        
        @Override
        public String toString() {
            return height + "p";
        }
    }
}
