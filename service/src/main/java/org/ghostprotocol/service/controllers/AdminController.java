package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.admin.AdminService;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.storage.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Timed
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers(
            @Auth AuthenticatedDevice auth,
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("100") int limit) {
        
        try {
            List<Account> users = adminService.listUsers(auth.getAccount(), offset, limit);
            return Response.ok(new ListUsersResponse(users)).build();
        } catch (AdminService.AdminAuthorizationException e) {
            return Response.status(Response.Status.FORBIDDEN)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @Timed
    @POST
    @Path("/users/{userId}/role")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignRole(
            @Auth AuthenticatedDevice auth,
            @PathParam("userId") UUID userId,
            AssignRoleRequest request) {
        
        try {
            adminService.assignRole(auth.getAccount(), userId, request.role);
            return Response.ok().build();
        } catch (AdminService.AdminAuthorizationException e) {
            return Response.status(Response.Status.FORBIDDEN)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @Timed
    @DELETE
    @Path("/users/{userId}/role")
    public Response removeRole(
            @Auth AuthenticatedDevice auth,
            @PathParam("userId") UUID userId) {
        
        try {
            adminService.removeRole(auth.getAccount(), userId);
            return Response.ok().build();
        } catch (AdminService.AdminAuthorizationException e) {
            return Response.status(Response.Status.FORBIDDEN)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @Timed
    @POST
    @Path("/users/{userId}/suspend")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response suspendUser(
            @Auth AuthenticatedDevice auth,
            @PathParam("userId") UUID userId,
            SuspendUserRequest request) {
        
        try {
            adminService.suspendUser(auth.getAccount(), userId, request.reason);
            return Response.ok().build();
        } catch (AdminService.AdminAuthorizationException e) {
            return Response.status(Response.Status.FORBIDDEN)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @Timed
    @POST
    @Path("/users/{userId}/unsuspend")
    public Response unsuspendUser(
            @Auth AuthenticatedDevice auth,
            @PathParam("userId") UUID userId) {
        
        try {
            adminService.unsuspendUser(auth.getAccount(), userId);
            return Response.ok().build();
        } catch (AdminService.AdminAuthorizationException e) {
            return Response.status(Response.Status.FORBIDDEN)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @Timed
    @POST
    @Path("/users/{userId}/tier")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserTier(
            @Auth AuthenticatedDevice auth,
            @PathParam("userId") UUID userId,
            UpdateTierRequest request) {
        
        try {
            adminService.updateUserTier(auth.getAccount(), userId, request.tierId);
            return Response.ok().build();
        } catch (AdminService.AdminAuthorizationException e) {
            return Response.status(Response.Status.FORBIDDEN)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @Timed
    @GET
    @Path("/metrics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMonitoringMetrics(@Auth AuthenticatedDevice auth) {
        try {
            AdminService.MonitoringMetrics metrics = adminService.getMonitoringMetrics(auth.getAccount());
            return Response.ok(metrics).build();
        } catch (AdminService.AdminAuthorizationException e) {
            return Response.status(Response.Status.FORBIDDEN)
                         .entity(e.getMessage())
                         .build();
        }
    }

    public static class ListUsersResponse {
        public List<Account> users;

        public ListUsersResponse(List<Account> users) {
            this.users = users;
        }
    }

    public static class AssignRoleRequest {
        public AdminRole role;
    }

    public static class SuspendUserRequest {
        public String reason;
    }

    public static class UpdateTierRequest {
        public String tierId;
    }
}
