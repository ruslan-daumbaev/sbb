package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.models.ScheduleModel;
import com.tsystems.sbb.models.TrainModel;

import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public interface TrainsService {

    List<TrainModel> getAllTrains();

    void addTrain(TrainModel trainModel);

    TrainModel getTrain(int trainId);

    List<ScheduleModel> findTrainsByParams(int fromStationId, int toStationId,
                                           String fromTime, String toTime);
}
