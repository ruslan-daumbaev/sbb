package com.tsystems.sbb.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rdaumbae on 28.07.2015.
 */
@Entity
@Table(name = "trains")
public class Train extends EntityBase {
    private String trainNumber;
    private int placesAmount;
    private Date insDate;
    private Date updDate;

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


    @Column(name = "insDate")
    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    @Column(name = "updDate")
    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
}
