package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.auth.UnifiedTwoFactorAuthService;
import org.ghostprotocol.service.auth.UnifiedTwoFactorAuthService.TwoFactorAuthType;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/2fa")
@Produces(MediaType.APPLICATION_JSON)
public class TwoFactorAuthController {
    private final UnifiedTwoFactorAuthService twoFactorAuthService;

    public TwoFactorAuthController(UnifiedTwoFactorAuthService twoFactorAuthService) {
        this.twoFactorAuthService = twoFactorAuthService;
    }

    @POST
    @Path("/setup/totp")
    public Response setupTOTP(@Auth Account account) {
        twoFactorAuthService.setupTOTP(account);
        return Response.ok().build();
    }

    @POST
    @Path("/setup/pattern")
    public Response setupPattern(
            @Auth Account account,
            @Auth Device device,
            @FormParam("pattern") String pattern) {
        twoFactorAuthService.setupPattern(account, device, pattern);
        return Response.ok().build();
    }

    @POST
    @Path("/setup/password")
    public Response setupPassword(
            @Auth Account account,
            @Auth Device device,
            @FormParam("password") String password) {
        twoFactorAuthService.setupPassword(account, device, password);
        return Response.ok().build();
    }

    @POST
    @Path("/setup/biometric")
    public Response setupBiometric(
            @Auth Account account,
            @Auth Device device,
            @FormParam("biometricKey") String biometricKey) {
        twoFactorAuthService.setupBiometric(account, device, biometricKey);
        return Response.ok().build();
    }

    @POST
    @Path("/verify")
    public Response verify(
            @Auth Account account,
            @Auth Device device,
            @FormParam("credential") String credential) {
        boolean valid = twoFactorAuthService.verify(account, device, credential);
        return Response.ok(valid).build();
    }

    @POST
    @Path("/disable")
    public Response disable(@Auth Account account, @Auth Device device) {
        twoFactorAuthService.disable(account, device);
        return Response.ok().build();
    }
}
