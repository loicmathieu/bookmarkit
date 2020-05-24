package fr.loicmathieu.bookmarkit;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/bookmarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookmarkResource {

    @GET
    public List<Bookmark> listBookmarks(){
        return Bookmark.listAll();
    }

    @GET
    @Path("/{id}")
    public Bookmark getBookmark(@PathParam("id") Long id) {
        return Bookmark.findById(id);
    }

    @POST
    @Transactional
    public Response createBookmark(Bookmark bookmark){
        bookmark.persist();
        return Response.created(URI.create("/bookmarks/" + bookmark.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateBookmark(Bookmark bookmark){
        Bookmark existing = Bookmark.findById(bookmark.id);
        existing.url = bookmark.url;
        existing.description = bookmark.description;
        existing.title = bookmark.title;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteBookmark(@PathParam("id")Long id){
        Bookmark existing = Bookmark.findById(id);
        existing.delete();
    }
}
