package fr.loicmathieu.bookmarkit;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bookmarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookmarkResource {

    public List<Bookmark> listBookmarks() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Bookmark getBookmark(Long id) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Response createBookmark(Bookmark bookmark) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Response updateBookmark(Bookmark bookmark, Long id) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Response deleteBookmark(Long id) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
