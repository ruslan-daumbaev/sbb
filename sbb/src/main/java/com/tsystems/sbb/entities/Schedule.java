package com.tsystems.sbb.entities;

import javax.persistence.*;
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
    private boolean isTrainStop;

    @Column(name = "trainTime")
    @Temporal(TemporalType.TIME)
    public Date getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(Date trainTime) {
        this.trainTime = trainTime;
    }

    @ManyToOne
    @JoinColumn(name = "trainId", insertable = true, updatable = true)
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @ManyToOne
    @JoinColumn(name = "stationId", insertable = true, updatable = true)
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Column(name = "isTrainStop")
    public boolean getIsTrainStop() {
        return isTrainStop;
    }

    public void setIsTrainStop(boolean isTrainStop) {
        this.isTrainStop = isTrainStop;
    }
}
