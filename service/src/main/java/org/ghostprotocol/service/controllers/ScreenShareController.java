package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.screenshare.ScreenShareService;
import org.ghostprotocol.service.storage.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/v1/screen-share")
public class ScreenShareController {
    private final ScreenShareService screenShareService;

    public ScreenShareController(ScreenShareService screenShareService) {
        this.screenShareService = screenShareService;
    }

    @Timed
    @POST
    @Path("/start/{targetUsername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startScreenShare(@Auth AuthenticatedDevice auth,
                                   @PathParam("targetUsername") String targetUsername) {
        Account sharer = auth.getAccount();
        // Lookup viewer account by username
        Account viewer = null; // TODO: Implement account lookup
        
        if (viewer == null) {
            return Response.status(404).build();
        }

        String sessionId = UUID.randomUUID().toString();
        screenShareService.startScreenShare(sharer, viewer, sessionId);

        return Response.ok(new StartScreenShareResponse(sessionId)).build();
    }

    @Timed
    @POST
    @Path("/stop/{sessionId}")
    public Response stopScreenShare(@Auth AuthenticatedDevice auth,
                                  @PathParam("sessionId") String sessionId) {
        screenShareService.stopScreenShare(sessionId);
        return Response.ok().build();
    }

    @Timed
    @POST
    @Path("/ghost/{sessionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleSignaling(@Auth AuthenticatedDevice auth,
                                  @PathParam("sessionId") String sessionId,
                                  GhostMessage message) {
        switch (message.type) {
            case "ice_candidate":
                screenShareService.handleIceCandidate(sessionId, message.payload);
                break;
            case "offer":
                screenShareService.handleOffer(sessionId, message.payload);
                break;
            case "answer":
                screenShareService.handleAnswer(sessionId, message.payload);
                break;
            default:
                return Response.status(400).build();
        }
        return Response.ok().build();
    }

    private static class StartScreenShareResponse {
        public String sessionId;

        public StartScreenShareResponse(String sessionId) {
            this.sessionId = sessionId;
        }
    }

    private static class GhostMessage {
        public String type;
        public String payload;
    }
}
