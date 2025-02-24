package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.screenshare.WebSocketSignalingService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/screenshare")
public class ScreenShareController {
    private final WebSocketSignalingService signalingService;

    public ScreenShareController(WebSocketSignalingService signalingService) {
        this.signalingService = signalingService;
    }

    @POST
    @Path("/signal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void handleSignal(SignalMessage message) {
        signalingService.handleSignal(message);
    }
}
