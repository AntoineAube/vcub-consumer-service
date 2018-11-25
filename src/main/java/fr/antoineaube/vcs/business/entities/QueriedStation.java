package fr.antoineaube.vcs.business.entities;

public class QueriedStation {

    private String name;
    private Position position;
    private float distanceToQueriedPosition;
    private int stationedBicyclesNumber;
    private int remainingPlacesNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public float getDistanceToQueriedPosition() {
        return distanceToQueriedPosition;
    }

    public void setDistanceToQueriedPosition(float distanceToQueriedPosition) {
        this.distanceToQueriedPosition = distanceToQueriedPosition;
    }

    public int getStationedBicyclesNumber() {
        return stationedBicyclesNumber;
    }

    public void setStationedBicyclesNumber(int stationedBicyclesNumber) {
        this.stationedBicyclesNumber = stationedBicyclesNumber;
    }

    public int getRemainingPlacesNumber() {
        return remainingPlacesNumber;
    }

    public void setRemainingPlacesNumber(int remainingPlacesNumber) {
        this.remainingPlacesNumber = remainingPlacesNumber;
    }
}
