package fr.loicmathieu.bookmarkit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bookmarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookmarkResource {

    public List<Bookmark> listAll(){
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Bookmark get(Long id) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Response create(Bookmark bookmark){
        throw new UnsupportedOperationException("not yet implemented");
    }

    public void update(Bookmark bookmark){
        throw new UnsupportedOperationException("not yet implemented");
    }

    public void delete(Long id){
        throw new UnsupportedOperationException("not yet implemented");
    }
}