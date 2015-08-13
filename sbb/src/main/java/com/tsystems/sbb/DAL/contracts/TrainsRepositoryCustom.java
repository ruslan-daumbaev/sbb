package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Train;

import java.util.List;

public interface TrainsRepositoryCustom {

    Train getTrainWithSchedules(int trainId);

    Train getTrainWithTrips(int trainId);

    List<Schedule> getTrainsByParams(int fromStationId,
                                     int toStationId);

}
