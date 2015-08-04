package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.models.TrainModel;

import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public interface TrainsService {
    List<TrainModel> getAllTrains();

    void addTrain(int trainId, String trainNumber, int placesAmount);

    TrainModel getTrain(int trainId);
}
