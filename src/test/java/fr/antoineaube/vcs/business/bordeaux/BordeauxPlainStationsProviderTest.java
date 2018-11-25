package fr.antoineaube.vcs.business.bordeaux;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BordeauxPlainStationsProviderTest {

    @Test
    public void shouldCreateACorrectRequest() {
        BordeauxPlainStationsProvider provider = new BordeauxPlainStationsProvider("ABC");

        assertEquals("http://data.lacub.fr/wfs?KEY=ABC&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=CI_VCUB_P&SRSNAME=EPSG%3A4326",
                provider.createRequest().getUrl());
    }

    @Test
    public void shouldFetchOneStation() throws IOException, URISyntaxException, UnirestException {
        HttpResponse<String> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.getStatus()).thenReturn(200);
        when(mockedResponse.getBody()).thenReturn(getXMLStations("stations-sample-1.xml"));

        HttpRequest mockedRequest = mock(HttpRequest.class);
        when(mockedRequest.asString()).thenReturn(mockedResponse);

        BordeauxPlainStationsProvider provider = spy(new BordeauxPlainStationsProvider("ABC"));
        when(provider.createRequest()).thenReturn(mockedRequest);

        assertEquals(1, provider.fetchBicyclesStations().size());
    }

    @Test
    public void shouldReturnAnEmptyListWhenWrongStatusCode() throws UnirestException {
        HttpResponse<String> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.getStatus()).thenReturn(400);

        HttpRequest mockedRequest = mock(HttpRequest.class);
        when(mockedRequest.asString()).thenReturn(mockedResponse);

        BordeauxPlainStationsProvider provider = spy(new BordeauxPlainStationsProvider("ABC"));
        when(provider.createRequest()).thenReturn(mockedRequest);

        assertEquals(0, provider.fetchBicyclesStations().size());
    }

    @Test
    public void shouldReturnAnEmptyListWhenRequestFails() throws UnirestException {
        HttpRequest mockedRequest = mock(HttpRequest.class);
        when(mockedRequest.asString()).thenThrow(new UnirestException("False exception!"));

        BordeauxPlainStationsProvider provider = spy(new BordeauxPlainStationsProvider("ABC"));
        when(provider.createRequest()).thenReturn(mockedRequest);

        assertEquals(0, provider.fetchBicyclesStations().size());
    }

    private String getXMLStations(String filename) throws URISyntaxException, IOException {
        URL resourceUrl = getClass().getClassLoader().getResource("stations/" + filename);
        assert resourceUrl != null;

        return new String(Files.readAllBytes(Paths.get(resourceUrl.toURI())), Charset.defaultCharset());
    }
}