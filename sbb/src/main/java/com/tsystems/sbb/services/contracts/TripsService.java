package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.models.TripDetailsModel;
import com.tsystems.sbb.models.TripModel;

import java.util.List;

public interface TripsService {

    List<TripModel> getCurrentTrips();

    TripDetailsModel getTripDetails(int tripId);
}
