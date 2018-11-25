package fr.antoineaube.vcs.business;

import fr.antoineaube.vcs.business.entities.BicyclesStation;
import fr.antoineaube.vcs.business.entities.Position;
import fr.antoineaube.vcs.business.entities.QueriedStation;

import java.util.Collections;
import java.util.List;

public class StationsFinder {

    private final PlainStationsProvider stationsProvider;

    public StationsFinder(PlainStationsProvider stationsProvider) {
        this.stationsProvider = stationsProvider;
    }

    public List<QueriedStation> findStations(Position location) {
        List<BicyclesStation> plainStations = stationsProvider.fetchBicyclesStations();

        return Collections.emptyList();
    }
}
