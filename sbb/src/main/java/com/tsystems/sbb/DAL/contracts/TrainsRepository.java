package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Station;
import com.tsystems.sbb.entities.Train;

import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public interface TrainsRepository extends BaseRepository<Train> {
    Train getTrainWithSchedules(int trainId);
}
