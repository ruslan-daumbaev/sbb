package com.tsystems.sbb.models;

import com.tsystems.sbb.entities.Station;

import java.util.Date;

public class StationModel {
    private String stationName;
    private int id;
    private Date insDate;
    private boolean isSelected;
    private String trainTime;

    public StationModel(){}

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

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }
}
