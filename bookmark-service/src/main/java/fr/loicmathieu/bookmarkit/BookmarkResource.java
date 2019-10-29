package fr.loicmathieu.bookmarkit;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
    public List<Bookmark> listBookmarks() {
        return Bookmark.listAll();
    }

    @GET
    @Path("{id}")
    public Bookmark getBookmark(@PathParam("id") Long id) {
        return Bookmark.findById(id);
    }

    @POST
    @Transactional
    public Response createBookmark(@Valid Bookmark bookmark) {
            bookmark.persist();
            return Response.status(Response.Status.CREATED).entity(bookmark).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateBookmark(@Valid Bookmark bookmark, @PathParam("id") Long id, @Context UriInfo uriInfo) {
        Bookmark entity = Bookmark.findById(id);
        entity.description = bookmark.description;
        entity.location = bookmark.location;
        entity.title = bookmark.title;
        entity.url = bookmark.url;
        return Response.created(URI.create(uriInfo.getPath())).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteBookmark(@PathParam("id") Long id) {
        Bookmark bookmark = Bookmark.findById(id);
        if (bookmark.isPersistent()) {
            bookmark.delete();
        }
        return Response.noContent().build();
    }
}
