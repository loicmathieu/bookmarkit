package fr.loicmathieu.bookmarkit;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/bookmarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookmarkResource {

    @GET
    @Operation(summary = "List all bookmarks")
    @Counted(name = "listBookmarks.count")
    @Timed(name = "listBookmarks.time")
    public List<Bookmark> listBookmarks() {
        return Bookmark.listAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get a bookmark")
    @Counted(name = "getBookmark.count")
    @Timed(name = "getBookmark.time")
    public Bookmark getBookmark(@PathParam("id") Long id) {
        return Bookmark.findById(id);
    }

    @POST
    @Transactional
    @Operation(summary = "Create a bookmark")
    @Counted(name = "createBookmark.count")
    @Timed(name = "createBookmark.time")
    public Response createBookmark(Bookmark bookmark) {
            bookmark.persist();
            return Response.status(Response.Status.CREATED).entity(bookmark).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Operation(summary = "Update a bookmark")
    @Counted(name = "updateBookmark.count")
    @Timed(name = "updateBookmark.time")
    public void updateBookmark(Bookmark bookmark, @PathParam("id") Long id) {
        Bookmark entity = Bookmark.findById(id);
        entity.description = bookmark.description;
        entity.location = bookmark.location;
        entity.title = bookmark.title;
        entity.url = bookmark.url;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Operation(summary = "Delete a bookmark")
    @Counted(name = "deleteBookmark.count")
    @Timed(name = "deleteBookmark.time")
    public Response deleteBookmark(@PathParam("id") Long id) {
        Bookmark bookmark = Bookmark.findById(id);
        if (bookmark != null) {
            bookmark.delete();
        }
        return Response.noContent().build();
    }
}
