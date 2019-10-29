package com.lahzouz.bookmarkit;

import org.apache.http.HttpStatus;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("/geoip")
@RegisterRestClient
public class GeoIpResource {

    @RestClient
    private GeoIpService geoIpService;
    private AtomicBoolean fail = new AtomicBoolean(true);
    private AtomicBoolean failIp = new AtomicBoolean(true);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGeoIpInfo() {

        if (fail.compareAndSet(true, false)) {
            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        } else {
            fail.getAndSet(true);
            return Response.ok(geoIpService.getCurrentIpInformation()).build();
        }
    }

    @GET
    @Path("/{ip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGeoIpInfo(@PathParam("ip") String ip) {

        if (failIp.compareAndSet(true, false)) {
            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        } else {
            failIp.getAndSet(true);
            return Response.ok(geoIpService.getIpInformation(ip)).build();
        }
    }
}
