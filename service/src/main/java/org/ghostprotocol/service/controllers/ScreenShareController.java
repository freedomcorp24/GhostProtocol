package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.screenshare.ScreenShareConfig;
import org.ghostprotocol.service.screenshare.ScreenShareService;
import org.ghostprotocol.service.screenshare.SignalMessage;
import org.ghostprotocol.service.screenshare.WebSocketSignalingService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;
import org.ghostprotocol.websocket.messages.WebSocketMessage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * REST API controller for screen sharing functionality.
 * Provides endpoints for initiating and managing screen sharing sessions.
 */
@Path("/v1/screenshare")
public class ScreenShareController {
    
    private final ScreenShareService screenShareService;
    private final WebSocketSignalingService signalingService;
    
    @Inject
    public ScreenShareController(
            ScreenShareService screenShareService,
            WebSocketSignalingService signalingService) {
        this.screenShareService = screenShareService;
        this.signalingService = signalingService;
    }
    
    /**
     * Handles WebRTC signaling messages
     */
    @POST
    @Path("/signal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleSignal(
            @Auth Account account,
            @Auth Device device,
            SignalMessage message) {
        
        // Convert SignalMessage to WebSocketMessage
        WebSocketMessage webSocketMessage = new WebSocketMessage();
        webSocketMessage.setType(message.getType());
        webSocketMessage.setSessionId(message.getSessionId());
        webSocketMessage.setSourceUserId(account.getUuid().toString());
        webSocketMessage.setTargetUserId(message.getTargetUserId());
        webSocketMessage.setSdp(message.getSdp());
        webSocketMessage.setIceCandidate(message.getIceCandidate());
        
        // Forward to signaling service
        signalingService.handleSignal(webSocketMessage);
        
        return Response.ok().build();
    }
    
    /**
     * Initiates a screen sharing session with another user
     */
    @POST
    @Path("/start")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startScreenShare(
            @Auth Account account,
            @Auth Device device,
            @FormParam("targetUserId") String targetUserId) {
        
        // Generate a unique session ID
        String sessionId = UUID.randomUUID().toString();
        
        // Create a WebSocket message to initiate screen sharing
        WebSocketMessage message = new WebSocketMessage();
        message.setType("start_screen_share");
        message.setSessionId(sessionId);
        message.setSourceUserId(account.getUuid().toString());
        message.setTargetUserId(targetUserId);
        
        // Forward the message to the signaling service
        signalingService.handleSignal(message);
        
        return Response.ok().entity("{\"sessionId\": \"" + sessionId + "\"}").build();
    }
    
    /**
     * Stops an active screen sharing session
     */
    @POST
    @Path("/stop")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response stopScreenShare(
            @Auth Account account,
            @Auth Device device,
            @FormParam("sessionId") String sessionId,
            @FormParam("targetUserId") String targetUserId) {
        
        // Create a WebSocket message to stop screen sharing
        WebSocketMessage message = new WebSocketMessage();
        message.setType("stop_screen_share");
        message.setSessionId(sessionId);
        message.setSourceUserId(account.getUuid().toString());
        message.setTargetUserId(targetUserId);
        
        // Forward the message to the signaling service
        signalingService.handleSignal(message);
        
        return Response.ok().build();
    }
    
    /**
     * Updates screen sharing configuration for an active session
     */
    @POST
    @Path("/config")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateConfig(
            @Auth Account account,
            @Auth Device device,
            @FormParam("sessionId") String sessionId,
            @FormParam("maxBitrate") int maxBitrate,
            @FormParam("minBitrate") int minBitrate,
            @FormParam("frameRate") int frameRate,
            @FormParam("videoCodec") String videoCodec,
            @FormParam("adaptiveResolution") boolean adaptiveResolution,
            @FormParam("adaptiveFrameRate") boolean adaptiveFrameRate,
            @FormParam("adaptiveBitrate") boolean adaptiveBitrate) {
        
        // Create a WebSocket message to update configuration
        WebSocketMessage message = new WebSocketMessage();
        message.setType("update_config");
        message.setSessionId(sessionId);
        message.setSourceUserId(account.getUuid().toString());
        
        // Set configuration parameters
        if (maxBitrate > 0) {
            message.setBitrate(maxBitrate);
        }
        
        if (frameRate > 0) {
            message.setFrameRate(frameRate);
        }
        
        if (videoCodec != null && !videoCodec.isEmpty()) {
            message.setVideoCodec(videoCodec);
        }
        
        // Forward the message to the screen share service
        screenShareService.handleMessage(sessionId, message);
        
        return Response.ok().build();
    }
    
    /**
     * Gets the status of an active screen sharing session
     */
    @GET
    @Path("/status/{sessionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionStatus(
            @Auth Account account,
            @Auth Device device,
            @PathParam("sessionId") String sessionId) {
        
        // Create a WebSocket message to request status
        WebSocketMessage message = new WebSocketMessage();
        message.setType("get_status");
        message.setSessionId(sessionId);
        message.setSourceUserId(account.getUuid().toString());
        
        // Forward the message to the screen share service
        screenShareService.handleMessage(sessionId, message);
        
        // Note: The actual status will be sent via WebSocket to the client
        // This endpoint just triggers the status request
        
        return Response.ok().build();
    }
}
