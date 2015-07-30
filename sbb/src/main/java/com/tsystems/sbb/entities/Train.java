package com.tsystems.sbb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
public class Train  extends EntityBase  {
    private String trainNumber;
    private int placesAmount;

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
}
