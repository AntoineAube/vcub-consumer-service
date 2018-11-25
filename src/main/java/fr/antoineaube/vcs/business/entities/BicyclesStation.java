package fr.antoineaube.vcs.business.entities;

import java.time.LocalDateTime;

public class BicyclesStation {

    private Position location;
    private int gid;
    private int identifier;
    private String type;
    private String name;
    private String state;
    private int bicyclesPlacesNumber;
    private int stationedBicyclesNumber;
    private LocalDateTime upToDateAt;

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBicyclesPlacesNumber() {
        return bicyclesPlacesNumber;
    }

    public void setBicyclesPlacesNumber(int bicyclesPlacesNumber) {
        this.bicyclesPlacesNumber = bicyclesPlacesNumber;
    }

    public int getStationedBicyclesNumber() {
        return stationedBicyclesNumber;
    }

    public void setStationedBicyclesNumber(int stationedBicyclesNumber) {
        this.stationedBicyclesNumber = stationedBicyclesNumber;
    }

    public LocalDateTime getUpToDateAt() {
        return upToDateAt;
    }

    public void setUpToDateAt(LocalDateTime upToDateAt) {
        this.upToDateAt = upToDateAt;
    }
}
