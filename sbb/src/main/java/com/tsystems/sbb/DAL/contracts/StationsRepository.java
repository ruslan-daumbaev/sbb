package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Station;

import java.util.List;

public interface StationsRepository {
    List<Station> getStations();

    Station getStation(int stationId);

    void saveStation(Station station);
}
