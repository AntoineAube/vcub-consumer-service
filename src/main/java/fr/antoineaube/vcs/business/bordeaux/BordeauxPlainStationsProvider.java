package fr.antoineaube.vcs.business.bordeaux;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import fr.antoineaube.vcs.business.PlainStationsProvider;
import fr.antoineaube.vcs.business.entities.BicyclesStation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class BordeauxPlainStationsProvider implements PlainStationsProvider {

    private static final Logger LOGGER = LogManager.getLogger(BordeauxPlainStationsProvider.class);
    private static final String BORDEAUX_WFS_API_URL = "http://data.lacub.fr/wfs";
    private static final XMLStationsParser STATIONS_PARSER = new XMLStationsParser();

    private final String apiKey;

    public BordeauxPlainStationsProvider() {
        apiKey = "9Y2RU3FTE8";//System.getenv("bordeaux-api-key"); TODO
    }

    public BordeauxPlainStationsProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public List<BicyclesStation> fetchBicyclesStations() {
        try {
            HttpResponse<String> response = createRequest().asString();

            if (response.getStatus() == 200) {
                return STATIONS_PARSER.translateStations(response.getBody());
            } else {
                LOGGER.error("Wrong query status: {}", response.getStatus());
                return Collections.emptyList();
            }
        } catch (UnirestException e) {
            LOGGER.error("Failed to fetch the stations", e);
            return Collections.emptyList();
        }
    }

    HttpRequest createRequest() {
        return Unirest.get(BORDEAUX_WFS_API_URL)
                .queryString("KEY", apiKey)
                .queryString("SERVICE", "WFS")
                .queryString("VERSION", "1.1.0")
                .queryString("REQUEST", "GetFeature")
                .queryString("TYPENAME", "CI_VCUB_P")
                .queryString("SRSNAME", "EPSG:4326");
    }
}
