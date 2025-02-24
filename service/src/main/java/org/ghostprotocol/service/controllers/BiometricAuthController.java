package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.auth.BiometricAuthenticationService;
import org.ghostprotocol.service.storage.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/biometric")
public class BiometricAuthController {
    private final BiometricAuthenticationService biometricAuthService;

    public BiometricAuthController(BiometricAuthenticationService biometricAuthService) {
        this.biometricAuthService = biometricAuthService;
    }

    @Timed
    @PUT
    @Path("/enable")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enableBiometricAuth(@Auth AuthenticatedDevice auth, 
                                      EnableBiometricRequest request) {
        Account account = auth.getAccount();
        biometricAuthService.enableBiometricAuth(account, auth.getDevice(), request.biometricPublicKey);
        return Response.ok().build();
    }

    @Timed
    @PUT
    @Path("/disable")
    public Response disableBiometricAuth(@Auth AuthenticatedDevice auth) {
        Account account = auth.getAccount();
        biometricAuthService.disableBiometricAuth(account, auth.getDevice());
        return Response.ok().build();
    }

    @Timed
    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBiometricStatus(@Auth AuthenticatedDevice auth) {
        Account account = auth.getAccount();
        boolean enabled = biometricAuthService.isBiometricAuthEnabled(account, auth.getDevice());
        return Response.ok(new BiometricStatusResponse(enabled)).build();
    }

    public static class EnableBiometricRequest {
        public String biometricPublicKey;
    }

    public static class BiometricStatusResponse {
        public boolean enabled;

        public BiometricStatusResponse(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
