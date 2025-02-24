package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.security.qr.QRCodeService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/qr")
@Produces(MediaType.APPLICATION_JSON)
public class QRCodeController {
    private final QRCodeService qrCodeService;
    
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }
    
    @GET
    @Path("/generate")
    public Response generateQRCode(@Auth Account account) {
        String qrCode = qrCodeService.generateQRCode(account);
        return Response.ok(qrCode).build();
    }
    
    @POST
    @Path("/regenerate")
    public Response regenerateQRCode(@Auth Account account) {
        qrCodeService.regenerateQRCode(account);
        return Response.ok().build();
    }
    
    @POST
    @Path("/verify")
    public Response verifyQRCode(
            @FormParam("ghostId") String ghostId,
            @FormParam("token") String token) {
        boolean valid = qrCodeService.verifyQRCode(ghostId, token);
        return Response.ok(valid).build();
    }
}
