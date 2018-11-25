package fr.antoineaube.vcs.business;

import fr.antoineaube.vcs.business.entities.BicyclesStation;
import fr.antoineaube.vcs.business.entities.Position;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class XMLStationsParserTest {

    private XMLStationsParser parser;

    @Before
    public void initialize() {
        parser = new XMLStationsParser();
    }

    @Test
    public void shouldTranslateOneStation() throws URISyntaxException, IOException {
        String fileContent = getXMLStations("stations-sample-1.xml");

        List<BicyclesStation> stations = parser.translateStations(fileContent);
        assertEquals(1, stations.size());

        BicyclesStation station = stations.get(0);

        assertEquals(new Position(-0.566225f, 44.893910f, "EPSG:4326"), station.getLocation());
        assertEquals(147, station.getGid());
        assertEquals(147, station.getIdentifier());
        assertEquals("Parc des Expositions", station.getName());
        assertEquals(19, station.getBicyclesPlacesNumber());
        assertEquals(10, station.getStationedBicyclesNumber());
        assertEquals(LocalDateTime.of(2018,11,25,17,35,3), station.getUpToDateAt());
    }

    @Test
    public void shouldTranslateMultipleStations() throws IOException, URISyntaxException {
        String fileContent = getXMLStations("stations-sample-2.xml");

        List<BicyclesStation> stations = parser.translateStations(fileContent);
        assertEquals(176, stations.size());
    }

    private String getXMLStations(String filename) throws URISyntaxException, IOException {
        URL resourceUrl = getClass().getClassLoader().getResource("stations/" + filename);
        assert resourceUrl != null;

        return new String(Files.readAllBytes(Paths.get(resourceUrl.toURI())), Charset.defaultCharset());
    }
}