package com.tsystems.sbb.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
@Table(name = "trains")
public class Train extends EntityBase {
    private String trainNumber;
    private int placesAmount;

    private List<Schedule> schedules;
    private List<Trip> trips;

    @Column(name = "trainNumber")
    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Column(name = "placesAmount")
    public int getPlacesAmount() {
        return placesAmount;
    }

    public void setPlacesAmount(int placesAmount) {
        this.placesAmount = placesAmount;
    }

    @OneToMany(mappedBy = "train", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }


    @OneToMany(mappedBy = "train", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

}
