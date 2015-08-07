package com.tsystems.sbb.entities;

import javax.persistence.*;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
@Table(name = "tickets")
public class Ticket extends EntityBase {
    private Trip trip;
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "passengerId", insertable = true, updatable = true)
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @ManyToOne
    @JoinColumn(name = "tripId", insertable = true, updatable = true)
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
