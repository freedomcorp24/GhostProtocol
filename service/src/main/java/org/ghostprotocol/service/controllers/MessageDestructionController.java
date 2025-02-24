package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.messaging.MessageDestructionService;
import org.ghostprotocol.service.storage.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/messages/destruction")
public class MessageDestructionController {
    private final MessageDestructionService destructionService;

    public MessageDestructionController(MessageDestructionService destructionService) {
        this.destructionService = destructionService;
    }

    @Timed
    @POST
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response scheduleDestruction(
            @Auth AuthenticatedDevice auth,
            @PathParam("messageId") String messageId,
            DestructionRequest request) {
        
        Account account = auth.getAccount();
        destructionService.scheduleMessageDestruction(messageId, account, request.destructionTime);
        return Response.ok().build();
    }

    @Timed
    @DELETE
    @Path("/{messageId}")
    public Response cancelDestruction(
            @Auth AuthenticatedDevice auth,
            @PathParam("messageId") String messageId) {
        
        destructionService.cancelMessageDestruction(messageId);
        return Response.ok().build();
    }

    @Timed
    @POST
    @Path("/{messageId}/destroy")
    public Response destroyNow(
            @Auth AuthenticatedDevice auth,
            @PathParam("messageId") String messageId) {
        
        Account account = auth.getAccount();
        destructionService.destroyMessage(messageId, account);
        return Response.ok().build();
    }

    public static class DestructionRequest {
        public long destructionTime;
    }
}
