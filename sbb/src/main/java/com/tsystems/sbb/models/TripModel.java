package com.tsystems.sbb.models;

import com.tsystems.sbb.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class TripModel {
    private int tripId;
    private String trainNumber;
    private String tripDate;
    private String tripTime;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = DateUtil.convertDateTimeToString(tripDate, "dd.MM.yyyy");
    }

    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(Date tripTime) {
        this.tripTime = DateUtil.convertDateTimeToString(tripTime, "HH:mm");
    }
}
