package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Trip;

import java.util.List;

public interface TripsRepositoryCustom {

    List<Trip> getCurrentTrips();

    Trip getTripDetails(int tripId);
}
