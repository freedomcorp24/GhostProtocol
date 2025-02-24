package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.storage.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/v1/vault")
public class VaultController {
    private final VaultStorageService vaultService;

    public VaultController(VaultStorageService vaultService) {
        this.vaultService = vaultService;
    }

    @Timed
    @POST
    @Path("/items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeItem(
            @Auth AuthenticatedDevice auth,
            StoreItemRequest request) {
        try {
            VaultItem item = createVaultItem(request);
            vaultService.storeItem(auth.getAccount(), item);
            return Response.ok(new StoreItemResponse(item.getId())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity("Failed to store item: " + e.getMessage())
                         .build();
        }
    }

    @Timed
    @GET
    @Path("/items/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(
            @Auth AuthenticatedDevice auth,
            @PathParam("itemId") String itemId,
            @QueryParam("type") VaultItemType type) {
        try {
            Optional<VaultItem> item = vaultService.getItem(auth.getAccount(), itemId, type);
            if (!item.isPresent()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(new GetItemResponse(item.get())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity("Failed to get item: " + e.getMessage())
                         .build();
        }
    }

    @Timed
    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listItems(
            @Auth AuthenticatedDevice auth,
            @QueryParam("type") VaultItemType type) {
        List<VaultItem> items = vaultService.listItems(auth.getAccount(), type);
        List<VaultItemSummary> summaries = items.stream()
            .map(VaultItemSummary::new)
            .collect(Collectors.toList());
        return Response.ok(new ListItemsResponse(summaries)).build();
    }

    @Timed
    @DELETE
    @Path("/items/{itemId}")
    public Response deleteItem(
            @Auth AuthenticatedDevice auth,
            @PathParam("itemId") String itemId) {
        vaultService.deleteItem(auth.getAccount(), itemId);
        return Response.ok().build();
    }

    private VaultItem createVaultItem(StoreItemRequest request) {
        switch (request.type) {
            case PASSWORD:
                return new PasswordVaultItem(request.name, request.username, request.password);
            case NOTE:
                return new NoteVaultItem(request.name, request.content);
            case FILE:
                return new FileVaultItem(request.name, request.fileData, request.mimeType);
            case CONTACT:
                return new ContactVaultItem(request.name, request.contactData);
            default:
                throw new IllegalArgumentException("Unsupported vault item type: " + request.type);
        }
    }

    public static class StoreItemRequest {
        public VaultItemType type;
        public String name;
        public String username;
        public String password;
        public String content;
        public byte[] fileData;
        public String mimeType;
        public String contactData;
    }

    public static class StoreItemResponse {
        public String itemId;

        public StoreItemResponse(String itemId) {
            this.itemId = itemId;
        }
    }

    public static class GetItemResponse {
        public VaultItem item;

        public GetItemResponse(VaultItem item) {
            this.item = item;
        }
    }

    public static class ListItemsResponse {
        public List<VaultItemSummary> items;

        public ListItemsResponse(List<VaultItemSummary> items) {
            this.items = items;
        }
    }

    public static class VaultItemSummary {
        public String id;
        public String name;
        public VaultItemType type;
        public long createdAt;

        public VaultItemSummary(VaultItem item) {
            this.id = item.getId();
            this.name = item.getName();
            this.type = item.getType();
            this.createdAt = item.getCreatedAt();
        }
    }
}
