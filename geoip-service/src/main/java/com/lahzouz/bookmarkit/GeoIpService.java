package com.lahzouz.bookmarkit;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json")
@RegisterRestClient
public interface GeoIpService {

    @GET
    @Path("/{ip}")
    @Produces(MediaType.APPLICATION_JSON)
    GeoIp getIpInformation(@PathParam("ip") String ip);

}