package com.tsystems.sbb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
@Table(name = "stations")
public class Station extends EntityBase  {
    private String stationName;

    @Column(name = "stationName")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
