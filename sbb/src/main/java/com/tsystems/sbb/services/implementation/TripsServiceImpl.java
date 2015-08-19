package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.DAL.contracts.TicketsRepository;
import com.tsystems.sbb.DAL.contracts.TripsRepository;
import com.tsystems.sbb.entities.Ticket;
import com.tsystems.sbb.entities.Trip;
import com.tsystems.sbb.models.PassengerModel;
import com.tsystems.sbb.models.TripDetailsModel;
import com.tsystems.sbb.models.TripModel;
import com.tsystems.sbb.services.contracts.TripsService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("tripsService")
public class TripsServiceImpl implements TripsService {

    @Autowired
    private TripsRepository tripsRepository;
    @Autowired
    private TicketsRepository ticketsRepository;

    public List<TripModel> getCurrentTrips() {
        LocalDate localDate = new LocalDate();
        localDate.minusDays(5);
        List<Trip> trips = tripsRepository.getCurrentTrips(localDate.toDate());
        List<TripModel> tripModels = new ArrayList<>(trips.size());
        for (Trip trip : trips) {
            TripModel tripModel = new TripModel();
            tripModel.setTripDate(trip.getTripDate());
            tripModel.setTrainNumber(trip.getTrain().getTrainNumber());
            tripModel.setTripId(trip.getId());
            tripModels.add(tripModel);
        }
        return tripModels;
    }

    public TripDetailsModel getTripDetails(int tripId) {
        TripDetailsModel tripDetailsModel = new TripDetailsModel();
        Trip trip = tripsRepository.getTripDetails(tripId);
        tripDetailsModel.setTripId(tripId);
        tripDetailsModel.setTripDate(trip.getTripDate());
        tripDetailsModel.setTrainNumber(trip.getTrain().getTrainNumber());
        tripDetailsModel.setPlacesAmount(trip.getTrain().getPlacesAmount());
        List<Ticket> tickets = ticketsRepository.findTicketsByTripId(tripId);
        for (Ticket ticket : tickets) {
            if (ticket.getPassenger() != null) {
                tripDetailsModel.addPassenger(new PassengerModel(ticket));
            }
        }
        return tripDetailsModel;
    }
}
