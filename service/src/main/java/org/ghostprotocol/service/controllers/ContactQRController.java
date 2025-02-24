package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.contacts.QRCodeService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Path("/v1/contacts/qr")
@Produces(MediaType.APPLICATION_JSON)
public class ContactQRController {
    private final QRCodeService qrCodeService;
    
    public ContactQRController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }
    
    @GET
    @Path("/generate")
    public Response generateContactQR(@Auth Account account) {
        CompletableFuture<String> qrCodeFuture = qrCodeService.generateQRCode(account.getUuid());
        try {
            String qrCode = qrCodeFuture.join();
            return Response.ok(qrCode).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to generate QR code: " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Path("/regenerate")
    public Response regenerateContactQR(@Auth Account account) {
        CompletableFuture<Void> future = qrCodeService.regenerateQRCode(account.getUuid());
        try {
            future.join();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to regenerate QR code: " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Path("/resolve")
    public Response resolveContactQR(@FormParam("qrCode") String qrCode) {
        CompletableFuture<UUID> userIdFuture = qrCodeService.resolveQRCode(qrCode);
        try {
            UUID userId = userIdFuture.join();
            return Response.ok(userId.toString()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid QR code: " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Path("/add")
    public Response addContact(
            @Auth Account account,
            @FormParam("contactId") String contactId) {
        try {
            UUID contactUuid = UUID.fromString(contactId);
            // Here you would add the contact to the user's contact list
            // This would typically involve a call to a ContactService
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid contact ID format")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add contact: " + e.getMessage())
                    .build();
        }
    }
}
