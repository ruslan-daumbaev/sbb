package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface TripsRepository extends CrudRepository<Trip, Integer> {
    List<Trip> findTripsByTrainId(int trainId);

    Trip findTripByTripDateAndTrainId(Date tripDate, int trainId);
}
