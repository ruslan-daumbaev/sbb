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

    @Query("select t from Ticket t " +
            "join fetch t.passenger p join fetch t.trip tr " +
            "join fetch tr.train " +
            "where t.insDate between ?1 and ?2 order by t.insDate")
    List<Ticket> findTicketsByInsDate(Date fromDate, Date toDate);
}
