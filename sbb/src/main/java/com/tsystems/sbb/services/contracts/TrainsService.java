package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.models.ScheduleModel;
import com.tsystems.sbb.models.TrainModel;

import java.util.List;

public interface TrainsService {

    List<TrainModel> getAllTrains();

    void saveTrain(TrainModel trainModel);

    TrainModel getTrain(int trainId);

    List<ScheduleModel> findTrainsByParams(int fromStationId, int toStationId,
                                           String fromTime, String toTime);
}
