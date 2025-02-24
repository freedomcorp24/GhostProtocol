package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.security.DecoyAccountService;
import org.ghostprotocol.service.security.DuressConfig;
import org.ghostprotocol.service.storage.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/v1/decoy")
public class DecoyAccountController {
    private final DecoyAccountService decoyAccountService;

    public DecoyAccountController(DecoyAccountService decoyAccountService) {
        this.decoyAccountService = decoyAccountService;
    }

    @Timed
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDecoyAccount(
            @Auth AuthenticatedDevice auth,
            CreateDecoyRequest request) {
        
        Account mainAccount = auth.getAccount();
        Account decoyAccount = decoyAccountService.createDecoyAccount(mainAccount, request.username);
        
        return Response.ok(new CreateDecoyResponse(decoyAccount.getUuid())).build();
    }

    @Timed
    @POST
    @Path("/activate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activateDecoyMode(
            @Auth AuthenticatedDevice auth,
            ActivateDecoyRequest request) {
        
        Account account = auth.getAccount();
        decoyAccountService.activateDecoyMode(account, request.duressPattern);
        
        return Response.ok().build();
    }

    @Timed
    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDecoyStatus(@Auth AuthenticatedDevice auth) {
        Account account = auth.getAccount();
        boolean isDecoyMode = decoyAccountService.isDecoyMode(account);
        
        return Response.ok(new DecoyStatusResponse(isDecoyMode)).build();
    }

    public static class CreateDecoyRequest {
        public String username;
        public List<UUID> emergencyContacts;
        public String duressPattern;
    }

    public static class CreateDecoyResponse {
        public UUID decoyAccountId;

        public CreateDecoyResponse(UUID decoyAccountId) {
            this.decoyAccountId = decoyAccountId;
        }
    }

    public static class ActivateDecoyRequest {
        public String duressPattern;
    }

    public static class DecoyStatusResponse {
        public boolean decoyModeActive;

        public DecoyStatusResponse(boolean decoyModeActive) {
            this.decoyModeActive = decoyModeActive;
        }
    }
}
