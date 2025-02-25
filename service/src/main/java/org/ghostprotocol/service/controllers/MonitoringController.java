package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.monitoring.MonitoringMetrics;
import org.ghostprotocol.service.monitoring.MonitoringService;
import org.ghostprotocol.service.storage.Account;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Path("/v1/monitoring")
@Produces(MediaType.APPLICATION_JSON)
public class MonitoringController {
    private final MonitoringService monitoringService;
    
    @Inject
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }
    
    @GET
    @Path("/metrics")
    public Response getMetrics(@Auth Account account) {
        // Only admin users should be able to access metrics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        CompletableFuture<MonitoringMetrics> future = monitoringService.getMetrics();
        try {
            MonitoringMetrics metrics = future.join();
            return Response.ok(metrics).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving metrics: " + e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/metrics/detailed")
    public Response getDetailedMetrics(@Auth Account account) {
        // Only admin users should be able to access detailed metrics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        CompletableFuture<Map<String, Object>> future = monitoringService.getDetailedMetrics();
        try {
            Map<String, Object> metrics = future.join();
            return Response.ok(metrics).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving detailed metrics: " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Path("/system/memory")
    public Response updateSystemMemoryUsage(
            @Auth Account account,
            @FormParam("bytes") long bytes) {
        // Only admin users should be able to update system metrics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        monitoringService.updateSystemMemoryUsage(bytes);
        return Response.ok().build();
    }
    
    @POST
    @Path("/system/cpu")
    public Response updateSystemCpuUsage(
            @Auth Account account,
            @FormParam("percent") double percent) {
        // Only admin users should be able to update system metrics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        monitoringService.updateSystemCpuUsage(percent);
        return Response.ok().build();
    }
    
    @POST
    @Path("/system/database")
    public Response updateDatabaseConnections(
            @Auth Account account,
            @FormParam("count") int count) {
        // Only admin users should be able to update system metrics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        monitoringService.updateDatabaseConnections(count);
        return Response.ok().build();
    }
    
    @POST
    @Path("/storage/usage")
    public Response updateStorageUsage(
            @Auth Account account,
            @FormParam("type") String type,
            @FormParam("bytes") long bytes) {
        // Only admin users should be able to update storage metrics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        monitoringService.updateStorageUsage(type, bytes);
        return Response.ok().build();
    }
    
    @POST
    @Path("/security/event")
    public Response recordSecurityEvent(
            @Auth Account account,
            @FormParam("type") String type,
            @FormParam("severity") String severity) {
        // Only admin users should be able to record security events
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        monitoringService.recordSecurityEvent(type, severity);
        return Response.ok().build();
    }
    
    @POST
    @Path("/security/suspicious-activity")
    public Response alertSuspiciousActivity(
            @Auth Account account,
            @FormParam("userId") String userId,
            @FormParam("activityType") String activityType) {
        // Only admin users should be able to alert suspicious activity
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        monitoringService.alertSuspiciousActivity(userId, activityType);
        return Response.ok().build();
    }
}
