package fr.antoineaube.vcs.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public interface BicyclesService {

    @GET
    @Path("/stations")
    Response findBicycleStations(@QueryParam("latitude") float latitude, @QueryParam("longitude") float longitude);
}
