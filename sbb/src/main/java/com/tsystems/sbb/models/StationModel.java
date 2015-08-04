package com.tsystems.sbb.models;

import com.tsystems.sbb.entities.Station;

import java.util.Date;

public class StationModel {
    private String stationName;
    private int id;
    private Date insDate;

    public StationModel(Station station){
        stationName = station.getStationName();
        id = station.getId();
        insDate = station.getInsDate();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }
}
