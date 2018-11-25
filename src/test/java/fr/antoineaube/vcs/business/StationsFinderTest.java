package fr.antoineaube.vcs.business;

import fr.antoineaube.vcs.business.bordeaux.BordeauxPlainStationsProvider;
import fr.antoineaube.vcs.business.entities.BicyclesStation;
import fr.antoineaube.vcs.business.entities.Position;
import fr.antoineaube.vcs.business.entities.QueriedStation;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StationsFinderTest {

    @Test
    public void shouldFindOneStation() {
        BicyclesStation foundStation = new BicyclesStation();
        foundStation.setLocation(new Position(0, 0, "EPSG:4326"));
        foundStation.setName("Station A");
        foundStation.setBicyclesPlacesNumber(20);
        foundStation.setStationedBicyclesNumber(10);

        BordeauxPlainStationsProvider mockedProvider = mock(BordeauxPlainStationsProvider.class);
        when(mockedProvider.fetchBicyclesStations()).thenReturn(Collections.singletonList(foundStation));

        StationsFinder stationsFinder = new StationsFinder(mockedProvider);
        List<QueriedStation> stations = stationsFinder.findStations(new Position(1, 0, "EPSG:4326"));

        assertEquals(1, stations.size());

        QueriedStation station = stations.get(0);

        assertEquals("Station A", station.getName());
        assertEquals(new Position(0, 0, "EPSG:4326"), station.getPosition());
        assertEquals(1f, station.getDistanceToQueriedPosition(), 0.001);
        assertEquals(10, station.getStationedBicyclesNumber());
        assertEquals(20, station.getRemainingPlacesNumber());
    }
}