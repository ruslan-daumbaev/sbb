package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Trip;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface TripsRepository extends CrudRepository<Trip, Integer>, TripsRepositoryCustom {
    Trip findTripByTripDateAndTrainId(Date tripDate, int trainId);

}
