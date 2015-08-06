package com.tsystems.sbb.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
@Table(name = "stations")
public class Station extends EntityBase  {
    private String stationName;
    private List<Schedule> schedules;

    @Column(name = "stationName")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @OneToMany(mappedBy = "station", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
