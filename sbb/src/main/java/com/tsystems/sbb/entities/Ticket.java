package com.tsystems.sbb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
public class Ticket extends EntityBase {
    @ManyToOne
    private Train train;
    @ManyToOne
    private Passenger passenger;
}
