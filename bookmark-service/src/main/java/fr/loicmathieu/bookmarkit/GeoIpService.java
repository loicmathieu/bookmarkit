package fr.loicmathieu.bookmarkit;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/geoip")
@RegisterRestClient
public interface GeoIpService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    GeoIp getIpInformation();

}
