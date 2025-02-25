package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.admin.AdminService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.subscription.SubscriptionTier;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * REST API controller for admin operations including user management,
 * premium account management, and product tier management.
 */
@Path("/v1/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {
    
    private final AdminService adminService;
    
    @Inject
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    /**
     * User Management Endpoints
     */
    
    @POST
    @Path("/users/{userId}/ban")
    public Response banUser(
            @Auth Account adminAccount,
            @PathParam("userId") String userIdStr,
            @FormParam("reason") String reason,
            @FormParam("duration") int duration) {
        
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            UUID userId = UUID.fromString(userIdStr);
            boolean success = adminService.banUser(adminAccount, userId, reason, duration);
            
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("You do not have permission to ban users")
                        .build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid user ID format")
                    .build();
        }
    }
    
    @POST
    @Path("/users/{userId}/unban")
    public Response unbanUser(
            @Auth Account adminAccount,
            @PathParam("userId") String userIdStr) {
        
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            UUID userId = UUID.fromString(userIdStr);
            boolean success = adminService.unbanUser(adminAccount, userId);
            
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("You do not have permission to unban users or user is not banned")
                        .build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid user ID format")
                    .build();
        }
    }
    
    @GET
    @Path("/users/{userId}/ban-status")
    public Response getBanStatus(
            @Auth Account adminAccount,
            @PathParam("userId") String userIdStr) {
        
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            UUID userId = UUID.fromString(userIdStr);
            AdminService.BannedUserRecord record = adminService.getBanRecord(userId);
            
            if (record == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("User is not banned")
                        .build();
            }
            
            return Response.ok(record).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid user ID format")
                    .build();
        }
    }
    
    @GET
    @Path("/users/banned")
    public Response getBannedUsers(@Auth Account adminAccount) {
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        List<AdminService.BannedUserRecord> bannedUsers = adminService.getAllBannedUsers(adminAccount);
        
        if (bannedUsers.isEmpty()) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You do not have permission to view banned users or no users are banned")
                    .build();
        }
        
        return Response.ok(bannedUsers).build();
    }
    
    /**
     * Premium Account Management Endpoints
     */
    
    @POST
    @Path("/users/{userId}/upgrade")
    public Response upgradeUserAccount(
            @Auth Account adminAccount,
            @PathParam("userId") String userIdStr,
            @FormParam("tierId") String tierId) {
        
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            UUID userId = UUID.fromString(userIdStr);
            
            // In a real implementation, this would look up the user account
            // For now, we'll just create a dummy account
            Account userAccount = new Account();
            userAccount.setUuid(userId);
            
            boolean success = adminService.upgradeUserAccount(adminAccount, userAccount, tierId);
            
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("You do not have permission to upgrade accounts or tier does not exist")
                        .build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid user ID format")
                    .build();
        }
    }
    
    /**
     * Product Tier Management Endpoints
     */
    
    @GET
    @Path("/tiers")
    public Response getAllTiers() {
        List<SubscriptionTier> tiers = adminService.getAllSubscriptionTiers();
        return Response.ok(tiers).build();
    }
    
    @GET
    @Path("/tiers/{id}")
    public Response getTier(@PathParam("id") String id) {
        SubscriptionTier tier = adminService.getSubscriptionTier(id);
        
        if (tier == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Tier not found")
                    .build();
        }
        
        return Response.ok(tier).build();
    }
    
    @POST
    @Path("/tiers")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createTier(
            @Auth Account adminAccount,
            @FormParam("id") String id,
            @FormParam("name") String name,
            @FormParam("price") double price,
            @FormParam("features") List<String> features) {
        
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        SubscriptionTier tier = adminService.createSubscriptionTier(
                adminAccount, id, name, price, features);
        
        if (tier == null) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You do not have permission to create tiers or tier ID already exists")
                    .build();
        }
        
        return Response.status(Response.Status.CREATED).entity(tier).build();
    }
    
    @PUT
    @Path("/tiers/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateTier(
            @Auth Account adminAccount,
            @PathParam("id") String id,
            @FormParam("name") String name,
            @FormParam("price") double price,
            @FormParam("features") List<String> features) {
        
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        SubscriptionTier tier = adminService.updateSubscriptionTier(
                adminAccount, id, name, price, features);
        
        if (tier == null) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You do not have permission to update tiers or tier does not exist")
                    .build();
        }
        
        return Response.ok(tier).build();
    }
    
    @DELETE
    @Path("/tiers/{id}")
    public Response deleteTier(
            @Auth Account adminAccount,
            @PathParam("id") String id) {
        
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        boolean success = adminService.deleteSubscriptionTier(adminAccount, id);
        
        if (success) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You do not have permission to delete tiers, tier does not exist, or you cannot delete the free tier")
                    .build();
        }
    }
}
