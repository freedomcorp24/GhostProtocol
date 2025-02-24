package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.security.screenshot.ScreenshotPreventionService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/screenshot")
@Produces(MediaType.APPLICATION_JSON)
public class ScreenshotPreventionController {
    private final ScreenshotPreventionService screenshotPreventionService;
    
    public ScreenshotPreventionController(ScreenshotPreventionService screenshotPreventionService) {
        this.screenshotPreventionService = screenshotPreventionService;
    }
    
    @POST
    @Path("/attempt")
    public Response reportAttempt(@Auth Account account, @Auth Device device) {
        screenshotPreventionService.handleScreenshotAttempt(account, device);
        return Response.ok().build();
    }
    
    @POST
    @Path("/enable")
    public Response enablePrevention(@Auth Account account, @Auth Device device) {
        screenshotPreventionService.enableScreenshotPrevention(device);
        return Response.ok().build();
    }
    
    @POST
    @Path("/disable")
    public Response disablePrevention(@Auth Account account, @Auth Device device) {
        screenshotPreventionService.disableScreenshotPrevention(device);
        return Response.ok().build();
    }
}
