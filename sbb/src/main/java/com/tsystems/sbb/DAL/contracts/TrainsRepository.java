package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Train;

import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
public interface TrainsRepository {
    List<Train> getAllTrains();
}
