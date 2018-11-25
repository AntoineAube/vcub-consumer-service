package fr.antoineaube.vcs.business.entities;

import java.util.Objects;

public class Position {

    private float longitude;
    private float latitude;
    private String coordinateSystemName;

    public Position(float longitude, float latitude, String coordinateSystemName) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.coordinateSystemName = coordinateSystemName;
    }

    public Position() {
        // Useful when parsing documents.
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getCoordinateSystemName() {
        return coordinateSystemName;
    }

    public void setCoordinateSystemName(String coordinateSystemName) {
        this.coordinateSystemName = coordinateSystemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Float.compare(position.longitude, longitude) == 0 &&
                Float.compare(position.latitude, latitude) == 0 &&
                Objects.equals(coordinateSystemName, position.coordinateSystemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, coordinateSystemName);
    }

    @Override
    public String toString() {
        return "Position{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", coordinateSystemName='" + coordinateSystemName + '\'' +
                '}';
    }
}
