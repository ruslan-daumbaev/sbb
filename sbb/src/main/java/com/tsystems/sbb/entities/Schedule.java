package com.tsystems.sbb.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Timer;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
@Table(name = "schedules")
public class Schedule extends EntityBase  {
    private Date trainTime;

    private Train train;
    private Station station;

    public Date getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(Date trainTime) {
        this.trainTime = trainTime;
    }

    @ManyToOne
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @ManyToOne
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
