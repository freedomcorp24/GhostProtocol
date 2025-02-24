package org.ghostprotocol.service.controllers;

import org.ghostprotocol.service.security.notes.PrivateNotesService;
import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.Device;
import org.ghostprotocol.service.storage.NoteVaultItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/v1/notes")
@Produces(MediaType.APPLICATION_JSON)
public class PrivateNotesController {
    private final PrivateNotesService privateNotesService;

    public PrivateNotesController(PrivateNotesService privateNotesService) {
        this.privateNotesService = privateNotesService;
    }

    @POST
    public Response createNote(
            @Auth Account account,
            @FormParam("title") String title,
            @FormParam("content") String content,
            @FormParam("category") String category,
            @FormParam("tags") List<String> tags,
            @FormParam("pinned") boolean isPinned) {
        UUID noteId = privateNotesService.createNote(account, title, content, category, tags, isPinned);
        return Response.ok(noteId).build();
    }

    @PUT
    @Path("/{noteId}")
    public Response updateNote(
            @Auth Account account,
            @PathParam("noteId") UUID noteId,
            @FormParam("title") String title,
            @FormParam("content") String content,
            @FormParam("category") String category,
            @FormParam("tags") List<String> tags,
            @FormParam("pinned") boolean isPinned) {
        privateNotesService.updateNote(account, noteId, title, content, category, tags, isPinned);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{noteId}")
    public Response deleteNote(
            @Auth Account account,
            @PathParam("noteId") UUID noteId) {
        privateNotesService.deleteNote(account, noteId);
        return Response.ok().build();
    }

    @GET
    @Path("/{noteId}")
    public Response getNote(
            @Auth Account account,
            @PathParam("noteId") UUID noteId) {
        return privateNotesService.getNote(account, noteId)
                .map(note -> Response.ok(note).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/category/{category}")
    public Response getNotesByCategory(
            @Auth Account account,
            @PathParam("category") String category) {
        List<NoteVaultItem> notes = privateNotesService.getNotesByCategory(account, category);
        return Response.ok(notes).build();
    }

    @GET
    @Path("/tag/{tag}")
    public Response getNotesByTag(
            @Auth Account account,
            @PathParam("tag") String tag) {
        List<NoteVaultItem> notes = privateNotesService.getNotesByTag(account, tag);
        return Response.ok(notes).build();
    }

    @GET
    @Path("/pinned")
    public Response getPinnedNotes(@Auth Account account) {
        List<NoteVaultItem> notes = privateNotesService.getPinnedNotes(account);
        return Response.ok(notes).build();
    }
}
