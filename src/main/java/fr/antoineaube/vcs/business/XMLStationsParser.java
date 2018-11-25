package fr.antoineaube.vcs.business;

import fr.antoineaube.vcs.business.entities.BicyclesStation;
import fr.antoineaube.vcs.business.entities.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMLStationsParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();

    public List<BicyclesStation> translateStations(String xmlContent) {
        try {
            List<BicyclesStation> stations = new ArrayList<>();

            Document xmlTree = buildTree(xmlContent);

            NodeList xmlStations = xmlTree.getElementsByTagName("bm:CI_VCUB_P");

            for (int i = 0; i < xmlStations.getLength(); i++) {
                stations.add(translateOneStation((Element) xmlStations.item(i)));
            }

            return stations;
        } catch (IOException | SAXException | ParserConfigurationException e) {
            return Collections.emptyList();
        }
    }

    private Document buildTree(String xmlContent) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = FACTORY.newDocumentBuilder();
        Document xmlTree = builder.parse(new InputSource(new StringReader(xmlContent)));
        xmlTree.getDocumentElement().normalize();

        return xmlTree;
    }

    private BicyclesStation translateOneStation(Element xmlStation) {
        BicyclesStation translatedStation = new BicyclesStation();

        Element pointElement = (Element) xmlStation.getElementsByTagName("gml:Point").item(0);
        Position location = new Position();
        location.setCoordinateSystemName(pointElement.getAttribute("srsName"));
        String[] coordinates = pointElement.getElementsByTagName("gml:pos").item(0).getTextContent().split(" ");
        location.setLatitude(Float.parseFloat(coordinates[0]));
        location.setLongitude(Float.parseFloat(coordinates[1]));
        translatedStation.setLocation(location);

        translatedStation.setGid(Integer.parseInt(xmlStation.getElementsByTagName("bm:GID").item(0).getTextContent()));
        translatedStation.setIdentifier(Integer.parseInt(xmlStation.getElementsByTagName("bm:IDENT").item(0).getTextContent()));
        translatedStation.setType(xmlStation.getElementsByTagName("bm:TYPE").item(0).getTextContent());
        translatedStation.setName(xmlStation.getElementsByTagName("bm:NOM").item(0).getTextContent());
        translatedStation.setState(xmlStation.getElementsByTagName("bm:ETAT").item(0).getTextContent());
        translatedStation.setBicyclesPlacesNumber(Integer.parseInt(xmlStation.getElementsByTagName("bm:NBPLACES").item(0).getTextContent()));
        translatedStation.setStationedBicyclesNumber(Integer.parseInt(xmlStation.getElementsByTagName("bm:NBVELOS").item(0).getTextContent()));
        translatedStation.setUpToDateAt(LocalDateTime.parse(xmlStation.getElementsByTagName("bm:HEURE").item(0).getTextContent(), FORMATTER));

        return translatedStation;
    }
}
