package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.security.EmergencyContactService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.AccountsManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/v1/emergency")
public class EmergencyContactController {
    private final EmergencyContactService emergencyContactService;
    private final AccountsManager accountsManager;

    public EmergencyContactController(
            EmergencyContactService emergencyContactService,
            AccountsManager accountsManager) {
        this.emergencyContactService = emergencyContactService;
        this.accountsManager = accountsManager;
    }

    @Timed
    @POST
    @Path("/contacts")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmergencyContact(
            @Auth AuthenticatedDevice auth,
            AddEmergencyContactRequest request) {
        
        Account account = auth.getAccount();
        Account contact = accountsManager.get(request.contactId)
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        
        emergencyContactService.addEmergencyContact(account, contact);
        return Response.ok().build();
    }

    @Timed
    @DELETE
    @Path("/contacts/{contactId}")
    public Response removeEmergencyContact(
            @Auth AuthenticatedDevice auth,
            @PathParam("contactId") UUID contactId) {
        
        Account account = auth.getAccount();
        Account contact = accountsManager.get(contactId)
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        
        emergencyContactService.removeEmergencyContact(account, contact);
        return Response.ok().build();
    }

    @Timed
    @GET
    @Path("/contacts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmergencyContacts(@Auth AuthenticatedDevice auth) {
        Account account = auth.getAccount();
        List<UUID> contacts = emergencyContactService.getEmergencyContacts(account);
        
        List<EmergencyContact> contactDetails = contacts.stream()
            .map(id -> accountsManager.get(id))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(contact -> new EmergencyContact(contact.getUuid(), contact.getUsername()))
            .collect(Collectors.toList());
        
        return Response.ok(new GetEmergencyContactsResponse(contactDetails)).build();
    }

    @Timed
    @POST
    @Path("/notify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response notifyEmergencyContacts(
            @Auth AuthenticatedDevice auth,
            NotifyEmergencyContactsRequest request) {
        
        Account account = auth.getAccount();
        emergencyContactService.notifyEmergencyContacts(account, request.type);
        return Response.ok().build();
    }

    public static class AddEmergencyContactRequest {
        public UUID contactId;
    }

    public static class EmergencyContact {
        public UUID id;
        public String username;

        public EmergencyContact(UUID id, String username) {
            this.id = id;
            this.username = username;
        }
    }

    public static class GetEmergencyContactsResponse {
        public List<EmergencyContact> contacts;

        public GetEmergencyContactsResponse(List<EmergencyContact> contacts) {
            this.contacts = contacts;
        }
    }

    public static class NotifyEmergencyContactsRequest {
        public EmergencyContactService.EmergencyType type;
    }
}
