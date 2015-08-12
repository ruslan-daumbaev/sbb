package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Schedule;
import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.entities.Train;

import java.util.List;
import java.util.Date;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public interface TrainsRepository extends BaseRepository<Train> {
    Train getTrainWithSchedules(int trainId);

    Train getTrainWithTrips(int trainId);

    List<Schedule> getTrainsByParams(int fromStationId,
                                     int toStationId, Date fromTime, Date toTime);
}
