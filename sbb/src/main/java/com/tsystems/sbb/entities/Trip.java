package com.tsystems.sbb.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip extends EntityBase {
    private Date tripDate;
    private Train train;
    private List<Ticket> tickets;

    @Column(name = "tripDate")
    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = true, updatable = true)
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @OneToMany(mappedBy = "trip", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


}
