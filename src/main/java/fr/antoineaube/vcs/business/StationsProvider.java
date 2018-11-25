package fr.antoineaube.vcs.business;

import fr.antoineaube.vcs.business.entities.BicyclesStation;

import java.util.List;

public interface StationsProvider {

    List<BicyclesStation> fetchBicyclesStations();
}
