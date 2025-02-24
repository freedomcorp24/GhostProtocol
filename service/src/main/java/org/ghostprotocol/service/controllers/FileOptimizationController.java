package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.admin.FileOptimizationService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;

import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/v1/files")
@Produces(MediaType.APPLICATION_JSON)
public class FileOptimizationController {
    private final FileOptimizationService fileOptimizationService;
    
    public FileOptimizationController(FileOptimizationService fileOptimizationService) {
        this.fileOptimizationService = fileOptimizationService;
    }
    
    @POST
    @Path("/optimize")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response optimizeFile(
            @Auth Account account,
            @FormDataParam("file") byte[] fileData,
            @FormDataParam("mimeType") String mimeType) {
        String fileId = UUID.randomUUID().toString();
        fileOptimizationService.scheduleOptimization(fileId, fileData, mimeType);
        return Response.ok(fileId).build();
    }
}
