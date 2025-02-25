package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.analytics.AnalyticsService;
import org.ghostprotocol.service.analytics.AnalyticsService.UserAction;
import org.ghostprotocol.service.storage.Account;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * REST API controller for analytics operations.
 */
@Path("/v1/analytics")
@Produces(MediaType.APPLICATION_JSON)
public class AnalyticsController {
    
    private final AnalyticsService analyticsService;
    
    @Inject
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }
    
    /**
     * Gets time series data for a specific metric within a time range.
     */
    @GET
    @Path("/time-series/{metric}")
    public Response getTimeSeriesData(
            @Auth Account account,
            @PathParam("metric") String metric,
            @QueryParam("start") String startStr,
            @QueryParam("end") String endStr) {
        
        // Only admin users should be able to access analytics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            Instant start = startStr != null ? Instant.parse(startStr) : Instant.now().minusDays(7);
            Instant end = endStr != null ? Instant.parse(endStr) : Instant.now();
            
            CompletableFuture<Map<Instant, Double>> future = analyticsService.getTimeSeriesData(metric, start, end);
            Map<Instant, Double> data = future.join();
            
            return Response.ok(data).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving time series data: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets aggregated time series data for a specific metric within a time range.
     */
    @GET
    @Path("/time-series/{metric}/aggregated")
    public Response getAggregatedTimeSeriesData(
            @Auth Account account,
            @PathParam("metric") String metric,
            @QueryParam("start") String startStr,
            @QueryParam("end") String endStr,
            @QueryParam("aggregation") @DefaultValue("60") int aggregation) {
        
        // Only admin users should be able to access analytics
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            Instant start = startStr != null ? Instant.parse(startStr) : Instant.now().minusDays(7);
            Instant end = endStr != null ? Instant.parse(endStr) : Instant.now();
            
            CompletableFuture<Map<Instant, Double>> future = 
                    analyticsService.getAggregatedTimeSeriesData(metric, start, end, aggregation);
            Map<Instant, Double> data = future.join();
            
            return Response.ok(data).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving aggregated time series data: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets user actions for a specific user within a time range.
     */
    @GET
    @Path("/users/{userId}/actions")
    public Response getUserActions(
            @Auth Account account,
            @PathParam("userId") String userId,
            @QueryParam("start") String startStr,
            @QueryParam("end") String endStr) {
        
        // Only admin users should be able to access user actions
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            Instant start = startStr != null ? Instant.parse(startStr) : Instant.now().minusDays(7);
            Instant end = endStr != null ? Instant.parse(endStr) : Instant.now();
            
            CompletableFuture<List<UserAction>> future = analyticsService.getUserActions(userId, start, end);
            List<UserAction> actions = future.join();
            
            return Response.ok(actions).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving user actions: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Records a user action for behavior analysis.
     */
    @POST
    @Path("/users/{userId}/actions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recordUserAction(
            @Auth Account account,
            @PathParam("userId") String userId,
            Map<String, Object> actionData) {
        
        // Only admin users should be able to record user actions
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            String action = (String) actionData.get("action");
            if (action == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Action is required")
                        .build();
            }
            
            @SuppressWarnings("unchecked")
            Map<String, String> metadata = (Map<String, String>) actionData.get("metadata");
            
            analyticsService.recordUserAction(userId, action, metadata);
            
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error recording user action: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets feature usage statistics.
     */
    @GET
    @Path("/feature-usage")
    public Response getFeatureUsage(@Auth Account account) {
        // Only admin users should be able to access feature usage
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            CompletableFuture<Map<String, Long>> future = analyticsService.getFeatureUsage();
            Map<String, Long> featureUsage = future.join();
            
            return Response.ok(featureUsage).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving feature usage: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets user retention statistics.
     */
    @GET
    @Path("/retention")
    public Response getRetentionStats(
            @Auth Account account,
            @QueryParam("cohort_start") String cohortStartStr,
            @QueryParam("cohort_end") String cohortEndStr,
            @QueryParam("periods") @DefaultValue("4") int periods,
            @QueryParam("period_days") @DefaultValue("7") int periodDays) {
        
        // Only admin users should be able to access retention stats
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            Instant cohortStart = cohortStartStr != null ? 
                    Instant.parse(cohortStartStr) : Instant.now().minusDays(28);
            Instant cohortEnd = cohortEndStr != null ? 
                    Instant.parse(cohortEndStr) : Instant.now().minusDays(21);
            
            CompletableFuture<Map<Integer, Double>> future = 
                    analyticsService.getRetentionStats(cohortStart, cohortEnd, periods, periodDays);
            Map<Integer, Double> retentionStats = future.join();
            
            return Response.ok(retentionStats).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving retention stats: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets conversion funnel statistics.
     */
    @GET
    @Path("/conversion/{funnel}")
    public Response getConversionStats(
            @Auth Account account,
            @PathParam("funnel") String funnel,
            @QueryParam("start") String startStr,
            @QueryParam("end") String endStr) {
        
        // Only admin users should be able to access conversion stats
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            Instant start = startStr != null ? Instant.parse(startStr) : Instant.now().minusDays(7);
            Instant end = endStr != null ? Instant.parse(endStr) : Instant.now();
            
            CompletableFuture<Map<String, Double>> future = 
                    analyticsService.getConversionStats(funnel, start, end);
            Map<String, Double> conversionStats = future.join();
            
            return Response.ok(conversionStats).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving conversion stats: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Creates a new conversion funnel.
     */
    @POST
    @Path("/conversion/{funnel}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createConversionFunnel(
            @Auth Account account,
            @PathParam("funnel") String funnel,
            List<String> steps) {
        
        // Only admin users should be able to create conversion funnels
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            if (steps == null || steps.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Steps are required")
                        .build();
            }
            
            boolean created = analyticsService.createConversionFunnel(funnel, steps);
            
            if (created) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.CONFLICT)
                        .entity("Funnel already exists")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error creating conversion funnel: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Gets a comprehensive analytics report.
     */
    @GET
    @Path("/report")
    public Response getAnalyticsReport(
            @Auth Account account,
            @QueryParam("start") String startStr,
            @QueryParam("end") String endStr) {
        
        // Only admin users should be able to access analytics reports
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            Instant start = startStr != null ? Instant.parse(startStr) : Instant.now().minusDays(7);
            Instant end = endStr != null ? Instant.parse(endStr) : Instant.now();
            
            CompletableFuture<Map<String, Object>> future = 
                    analyticsService.getAnalyticsReport(start, end);
            Map<String, Object> report = future.join();
            
            return Response.ok(report).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error generating analytics report: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Records a data point in the time series data.
     */
    @POST
    @Path("/time-series/{metric}")
    public Response recordTimeSeriesData(
            @Auth Account account,
            @PathParam("metric") String metric,
            @FormParam("value") double value) {
        
        // Only admin users should be able to record time series data
        if (!account.isAdmin()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        try {
            analyticsService.recordTimeSeriesData(metric, value);
            
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error recording time series data: " + e.getMessage())
                    .build();
        }
    }
}
