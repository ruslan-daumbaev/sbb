package com.tsystems.sbb.models;

import com.tsystems.sbb.entities.Train;

import java.util.Date;

public class TrainModel {
    private String trainNumber;
    private int placesAmount;
    private int id;
    private Date insDate;

    public TrainModel(Train train){
        trainNumber = train.getTrainNumber();
        placesAmount = train.getPlacesAmount();
        setId(train.getId());
        setInsDate(train.getInsDate());
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getPlacesAmount() {
        return placesAmount;
    }

    public void setPlacesAmount(int placesAmount) {
        this.placesAmount = placesAmount;
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
