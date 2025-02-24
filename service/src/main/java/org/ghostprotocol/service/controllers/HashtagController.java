package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.admin.HashtagService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/v1/hashtags")
@Produces(MediaType.APPLICATION_JSON)
public class HashtagController {
    private final HashtagService hashtagService;
    
    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }
    
    @POST
    @Path("/add")
    public Response addHashtag(
            @Auth Account account,
            @FormParam("messageId") String messageId,
            @FormParam("hashtag") String hashtag,
            @FormParam("context") String context) {
        hashtagService.addHashtag(messageId, hashtag, context);
        return Response.ok().build();
    }
    
    @POST
    @Path("/remove")
    public Response removeHashtag(
            @Auth Account account,
            @FormParam("messageId") String messageId,
            @FormParam("hashtag") String hashtag,
            @FormParam("context") String context) {
        hashtagService.removeHashtag(messageId, hashtag, context);
        return Response.ok().build();
    }
    
    @GET
    @Path("/messages/{hashtag}")
    public Response getMessagesByHashtag(
            @Auth Account account,
            @PathParam("hashtag") String hashtag) {
        Set<String> messages = hashtagService.getMessagesByHashtag(hashtag);
        return Response.ok(messages).build();
    }
    
    @GET
    @Path("/group/{groupId}")
    public Response getGroupHashtags(
            @Auth Account account,
            @PathParam("groupId") String groupId) {
        Set<String> hashtags = hashtagService.getGroupHashtags(groupId);
        return Response.ok(hashtags).build();
    }
    
    @GET
    @Path("/channel/{channelId}")
    public Response getChannelHashtags(
            @Auth Account account,
            @PathParam("channelId") String channelId) {
        Set<String> hashtags = hashtagService.getChannelHashtags(channelId);
        return Response.ok(hashtags).build();
    }
    
    @GET
    @Path("/search/{prefix}")
    public Response searchHashtags(
            @Auth Account account,
            @PathParam("prefix") String prefix) {
        Set<String> hashtags = hashtagService.searchHashtags(prefix);
        return Response.ok(hashtags).build();
    }
}
