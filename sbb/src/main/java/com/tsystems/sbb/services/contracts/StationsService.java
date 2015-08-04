package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.models.TrainModel;

import java.util.List;

public interface StationsService {
    List<StationModel> getAllStations();

    void addStation(int stationId, String stationName);

    StationModel getStation(int stationId);
}
