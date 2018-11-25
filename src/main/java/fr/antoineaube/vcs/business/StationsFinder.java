package fr.antoineaube.vcs.business;

import fr.antoineaube.vcs.business.entities.BicyclesStation;
import fr.antoineaube.vcs.business.entities.Position;
import fr.antoineaube.vcs.business.entities.QueriedStation;

import java.util.List;
import java.util.stream.Collectors;

public class StationsFinder {

    private final PlainStationsProvider stationsProvider;

    public StationsFinder(PlainStationsProvider stationsProvider) {
        this.stationsProvider = stationsProvider;
    }

    public List<QueriedStation> findStations(Position location) {
        return stationsProvider.fetchBicyclesStations().stream()
                .map(plainStation -> enhanceStation(plainStation, location))
                .collect(Collectors.toList());
    }

    private QueriedStation enhanceStation(BicyclesStation plainStation, Position queriedLocation) {
        QueriedStation queriedStation = new QueriedStation();

        queriedStation.setName(plainStation.getName());
        queriedStation.setPosition(plainStation.getLocation());
        queriedStation.setDistanceToQueriedPosition(distanceBetweenPosition(plainStation.getLocation(), queriedLocation));
        queriedStation.setRemainingPlacesNumber(plainStation.getBicyclesPlacesNumber());
        queriedStation.setStationedBicyclesNumber(plainStation.getStationedBicyclesNumber());

        return queriedStation;
    }

    private static float distanceBetweenPosition(Position a, Position b) {
        // TODO Take the coordinates system into account.

        return (float) Math.sqrt(Math.pow((a.getLatitude() - b.getLatitude()), 2) + Math.pow(a.getLongitude() - b.getLongitude(), 2));
    }
}
