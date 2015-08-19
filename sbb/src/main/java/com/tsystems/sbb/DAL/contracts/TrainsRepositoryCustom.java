package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Train;

import java.util.List;

public interface TrainsRepositoryCustom {

    List<Schedule> getTrainsByParams(int fromStationId,
                                     int toStationId);

}
