package com.tsystems.sbb.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
@Table(name = "passengers")
public class Passenger extends EntityBase {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Ticket> tickets;

    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "birthDate")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @OneToMany(mappedBy = "passenger", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
