package fr.loicmathieu.bookmarkit;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    private static final Logger LOG = Logger.getLogger(BookmarkResource.class);

    @ConfigProperty(name = "greeting")
    private String greeting;

    @Channel("bookmarks")
    private Emitter<Bookmark> emitter;

    @RestClient
    GeoIpService geoIpService;

    @PostConstruct
    void init() {
        LOG.infof("Hello %s", greeting);
    }

    @GET
    @Operation(summary = "List all bookmarks")
    @Counted(name = "listAll.count")
    @Timed(name = "listAll.time")
    public List<Bookmark> listAll() {
        return Bookmark.listAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get a bookmark")
    @Counted(name = "get.count")
    @Timed(name = "get.time")
    public Bookmark get(@PathParam("id") Long id) {
        return Bookmark.findById(id);
    }

    @POST
    @Transactional
    @Operation(summary = "Create a bookmark")
    @Counted(name = "create.count")
    @Timed(name = "create.time")
    @Retry(maxRetries = 2)
    public Response create(@Valid Bookmark bookmark) {
        GeoIp geoIp = geoIpService.getIpInfos();
        if (geoIp != null) {
            bookmark.location = geoIp.city;
            bookmark.persist();
            this.emitter.send(bookmark);
            return Response.status(Response.Status.CREATED).entity(bookmark).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Operation(summary = "Update a bookmark")
    @Counted(name = "update.count")
    @Timed(name = "update.time")
    public Response update(@Valid Bookmark bookmark, @PathParam("id") Long id, @Context UriInfo uriInfo) {
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
    @Operation(summary = "Delete a bookmark")
    @Counted(name = "delete.count")
    @Timed(name = "delete.time")
    public Response delete(@PathParam("id") Long id) {
        Bookmark bookmark = Bookmark.findById(id);
        bookmark.delete();
        return Response.noContent().build();
    }
}
