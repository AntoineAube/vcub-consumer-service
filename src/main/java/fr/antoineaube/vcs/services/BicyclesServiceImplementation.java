package fr.antoineaube.vcs.services;

import fr.antoineaube.vcs.business.StationsFinder;
import fr.antoineaube.vcs.business.bordeaux.BordeauxPlainStationsProvider;
import fr.antoineaube.vcs.business.entities.Position;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("bicycles")
public class BicyclesServiceImplementation implements BicyclesService {

    private StationsFinder stationsFinder = new StationsFinder(new BordeauxPlainStationsProvider());

    @Override
    public Response findBicycleStations(float latitude, float longitude) {
        Position userLocation = new Position(latitude, longitude, "EPSG:4326");

        return Response.ok(stationsFinder.findStations(userLocation)).build();
    }
}
