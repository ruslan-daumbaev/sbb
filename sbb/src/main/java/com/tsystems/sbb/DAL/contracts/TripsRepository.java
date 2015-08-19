package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.entities.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface TripsRepository extends CrudRepository<Trip, Integer> {

    Trip findTripByTripDateAndTrainId(Date tripDate, int trainId);

    @Query("select t from Trip t join fetch t.train tr where t.id =?1")
    Trip getTripDetails(int tripId);

    @Query("select t from Trip t join fetch t.train tr where t.tripDate >=?1 order by t.insDate")
    List<Trip> getCurrentTrips(Date fromDate);
}
