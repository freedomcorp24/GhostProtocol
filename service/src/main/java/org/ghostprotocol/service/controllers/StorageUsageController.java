package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.storage.StorageUsageTrackingService;
import org.ghostprotocol.service.storage.StorageUsageTrackingService.StorageUsageReport;
import org.ghostprotocol.service.storage.VaultItemType;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.SubscriptionManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * REST API controller for storage usage tracking operations.
 */
@Path("/v1/storage/usage")
@Produces(MediaType.APPLICATION_JSON)
public class StorageUsageController {
    
    private final StorageUsageTrackingService storageUsageTrackingService;
    private final SubscriptionManager subscriptionManager;
    
    @Inject
    public StorageUsageController(
            StorageUsageTrackingService storageUsageTrackingService,
            SubscriptionManager subscriptionManager) {
        this.storageUsageTrackingService = storageUsageTrackingService;
        this.subscriptionManager = subscriptionManager;
    }
    
    /**
     * Gets the current storage usage for the authenticated user.
     */
    @GET
    @Path("/current")
    public Response getCurrentStorageUsage(@Auth Account account) {
        try {
            UUID userId = account.getUuid();
            
            CompletableFuture<Long> future = storageUsageTrackingService.getCurrentStorageUsage(userId);
            long currentUsage = future.join();
            
            return Response.ok(Map.of("usage", currentUsage)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving current storage usage: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets the current storage usage for the authenticated user, broken down by item type.
     */
    @GET
    @Path("/by-type")
    public Response getStorageUsageByType(@Auth Account account) {
        try {
            UUID userId = account.getUuid();
            
            CompletableFuture<Map<VaultItemType, Long>> future = 
                    storageUsageTrackingService.getStorageUsageByType(userId);
            Map<VaultItemType, Long> usageByType = future.join();
            
            return Response.ok(usageByType).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving storage usage by type: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets the storage usage history for the authenticated user within a time range.
     */
    @GET
    @Path("/history")
    public Response getStorageUsageHistory(
            @Auth Account account,
            @QueryParam("start") String startStr,
            @QueryParam("end") String endStr) {
        
        try {
            UUID userId = account.getUuid();
            
            Instant start = startStr != null ? Instant.parse(startStr) : Instant.now().minusDays(30);
            Instant end = endStr != null ? Instant.parse(endStr) : Instant.now();
            
            CompletableFuture<List<StorageUsageTrackingService.StorageUsageEvent>> future = 
                    storageUsageTrackingService.getStorageUsageHistory(userId, start, end);
            List<StorageUsageTrackingService.StorageUsageEvent> history = future.join();
            
            return Response.ok(history).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving storage usage history: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets the storage usage trend for the authenticated user within a time range.
     */
    @GET
    @Path("/trend")
    public Response getStorageUsageTrend(
            @Auth Account account,
            @QueryParam("start") String startStr,
            @QueryParam("end") String endStr,
            @QueryParam("period_hours") @DefaultValue("24") int periodHours) {
        
        try {
            UUID userId = account.getUuid();
            
            Instant start = startStr != null ? Instant.parse(startStr) : Instant.now().minusDays(30);
            Instant end = endStr != null ? Instant.parse(endStr) : Instant.now();
            
            CompletableFuture<Map<Instant, Long>> future = 
                    storageUsageTrackingService.getStorageUsageTrend(userId, start, end, periodHours);
            Map<Instant, Long> trend = future.join();
            
            return Response.ok(trend).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving storage usage trend: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets the storage quota for the authenticated user.
     */
    @GET
    @Path("/quota")
    public Response getStorageQuota(@Auth Account account) {
        try {
            UUID userId = account.getUuid();
            
            // Get subscription information
            CompletableFuture<Optional<SubscriptionManager.SubscriptionInformation>> subInfoFuture = 
                    subscriptionManager.getSubscriptionInformation(
                            new SubscriptionManager.SubscriberCredentials(userId));
            
            Optional<SubscriptionManager.SubscriptionInformation> subInfo = subInfoFuture.join();
            boolean isPremium = subInfo.isPresent();
            Instant subscriptionStart = subInfo
                    .map(info -> Instant.ofEpochSecond(info.getStartTimestamp()))
                    .orElse(Instant.now());
            
            CompletableFuture<Long> quotaFuture = 
                    storageUsageTrackingService.getStorageQuota(userId, isPremium, subscriptionStart);
            long quota = quotaFuture.join();
            
            return Response.ok(Map.of("quota", quota)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving storage quota: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets a comprehensive storage usage report for the authenticated user.
     */
    @GET
    @Path("/report")
    public Response getStorageUsageReport(@Auth Account account) {
        try {
            UUID userId = account.getUuid();
            
            // Get subscription information
            CompletableFuture<Optional<SubscriptionManager.SubscriptionInformation>> subInfoFuture = 
                    subscriptionManager.getSubscriptionInformation(
                            new SubscriptionManager.SubscriberCredentials(userId));
            
            Optional<SubscriptionManager.SubscriptionInformation> subInfo = subInfoFuture.join();
            boolean isPremium = subInfo.isPresent();
            Instant subscriptionStart = subInfo
                    .map(info -> Instant.ofEpochSecond(info.getStartTimestamp()))
                    .orElse(Instant.now());
            
            CompletableFuture<StorageUsageReport> reportFuture = 
                    storageUsageTrackingService.getStorageUsageReport(userId, isPremium, subscriptionStart);
            StorageUsageReport report = reportFuture.join();
            
            return Response.ok(report).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error generating storage usage report: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Admin endpoint to get storage usage for a specific user.
     */
    @GET
    @Path("/admin/users/{userId}")
    public Response getStorageUsageForUser(
            @Auth Account adminAccount,
            @PathParam("userId") String userIdStr) {
        
        // Only admin users should be able to access other users' storage usage
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            UUID userId = UUID.fromString(userIdStr);
            
            // Get subscription information
            CompletableFuture<Optional<SubscriptionManager.SubscriptionInformation>> subInfoFuture = 
                    subscriptionManager.getSubscriptionInformation(
                            new SubscriptionManager.SubscriberCredentials(userId));
            
            Optional<SubscriptionManager.SubscriptionInformation> subInfo = subInfoFuture.join();
            boolean isPremium = subInfo.isPresent();
            Instant subscriptionStart = subInfo
                    .map(info -> Instant.ofEpochSecond(info.getStartTimestamp()))
                    .orElse(Instant.now());
            
            CompletableFuture<StorageUsageReport> reportFuture = 
                    storageUsageTrackingService.getStorageUsageReport(userId, isPremium, subscriptionStart);
            StorageUsageReport report = reportFuture.join();
            
            return Response.ok(report).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid user ID format")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving storage usage for user: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Admin endpoint to get aggregated storage usage statistics across all users.
     */
    @GET
    @Path("/admin/aggregate")
    public Response getAggregateStorageUsage(@Auth Account adminAccount) {
        // Only admin users should be able to access aggregate storage usage
        if (!adminAccount.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        // This would typically query a database or analytics service for aggregate statistics
        // For now, we'll return a placeholder response
        return Response.status(Response.Status.NOT_IMPLEMENTED)
                .entity("Aggregate storage usage statistics not yet implemented")
                .build();
    }
}
