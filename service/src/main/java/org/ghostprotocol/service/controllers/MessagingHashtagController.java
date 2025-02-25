package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.messaging.HashtagService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Path("/v1/messaging/hashtags")
@Produces(MediaType.APPLICATION_JSON)
public class MessagingHashtagController {
    private final HashtagService hashtagService;
    
    public MessagingHashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }
    
    @POST
    @Path("/extract")
    public Response extractHashtags(
            @Auth Account account,
            @FormParam("message") String message) {
        try {
            CompletableFuture<Set<String>> future = hashtagService.extractHashtags(message);
            Set<String> hashtags = future.join();
            return Response.ok(hashtags).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error extracting hashtags: " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Path("/index")
    public Response indexHashtags(
            @Auth Account account,
            @FormParam("messageId") String messageId,
            @FormParam("hashtags") Set<String> hashtags) {
        try {
            UUID messageUuid = UUID.fromString(messageId);
            CompletableFuture<Void> future = hashtagService.indexHashtags(messageUuid, hashtags);
            future.join();
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid message ID format")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error indexing hashtags: " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Path("/index/group")
    public Response indexHashtagsForGroup(
            @Auth Account account,
            @FormParam("messageId") String messageId,
            @FormParam("groupId") String groupId,
            @FormParam("hashtags") Set<String> hashtags) {
        try {
            UUID messageUuid = UUID.fromString(messageId);
            UUID groupUuid = UUID.fromString(groupId);
            CompletableFuture<Void> future = hashtagService.indexHashtagsForGroup(messageUuid, groupUuid, hashtags);
            future.join();
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid ID format")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error indexing hashtags for group: " + e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/find/{hashtag}")
    public Response findMessagesByHashtag(
            @Auth Account account,
            @PathParam("hashtag") String hashtag,
            @QueryParam("groupId") String groupId) {
        try {
            UUID groupUuid = groupId != null ? UUID.fromString(groupId) : null;
            CompletableFuture<Set<UUID>> future = hashtagService.findMessagesByHashtag(hashtag, groupUuid);
            Set<UUID> messageIds = future.join();
            return Response.ok(messageIds).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid group ID format")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error finding messages by hashtag: " + e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/group/{groupId}")
    public Response getHashtagsInGroup(
            @Auth Account account,
            @PathParam("groupId") String groupId) {
        try {
            UUID groupUuid = UUID.fromString(groupId);
            CompletableFuture<Set<String>> future = hashtagService.getHashtagsInGroup(groupUuid);
            Set<String> hashtags = future.join();
            return Response.ok(hashtags).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid group ID format")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error getting hashtags in group: " + e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/trending")
    public Response getTrendingHashtags(
            @Auth Account account,
            @QueryParam("limit") @DefaultValue("10") int limit) {
        try {
            CompletableFuture<List<String>> future = hashtagService.getTrendingHashtags(limit);
            List<String> hashtags = future.join();
            return Response.ok(hashtags).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error getting trending hashtags: " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Path("/remove")
    public Response removeMessage(
            @Auth Account account,
            @FormParam("messageId") String messageId) {
        try {
            UUID messageUuid = UUID.fromString(messageId);
            CompletableFuture<Void> future = hashtagService.removeMessage(messageUuid);
            future.join();
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid message ID format")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error removing message: " + e.getMessage())
                    .build();
        }
    }
}
