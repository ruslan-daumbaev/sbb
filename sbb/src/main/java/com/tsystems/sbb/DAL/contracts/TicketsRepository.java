package com.tsystems.sbb.DAL.contracts;

import com.tsystems.sbb.entities.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Date;

public interface TicketsRepository extends CrudRepository<Ticket, Integer> {

    List<Ticket> findTicketsByTripId(int tripId);

    List<Ticket> findByTripIdAndPassengerFirstNameAndPassengerLastNameAndPassengerBirthDate(int tripId,
                                                                       String passengerFirstName,
                                                                       String passengerLastName,
                                                                       Date passengerBirthDate);

    @Query("select count (t) from Ticket t where t.trip.id = ?1")
    int getTicketsCountByTripId(int tripId);
}
