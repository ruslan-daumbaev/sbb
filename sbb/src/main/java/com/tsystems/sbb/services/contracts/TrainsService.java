package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.entities.Train;

import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public interface TrainsService {
    List<Train> getAllTrains();

    void addTrain(int trainId, String trainNumber, int placesAmount);

    Train getTrain(int trainId);
}
