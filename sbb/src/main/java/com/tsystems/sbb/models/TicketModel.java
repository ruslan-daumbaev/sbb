package com.tsystems.sbb.models;

import com.tsystems.sbb.entities.Passenger;
import com.tsystems.sbb.entities.Ticket;
import com.tsystems.sbb.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketModel extends PassengerModel {
    public static final String TRIP_DATE_FORMAT = "dd/MM/yyyy";
    private int trainId;
    private int scheduleId;
    private String trainNumber;
    private String trainTime;
    private String stationName;
    private String tripDate;

    public TicketModel(){}

    public TicketModel(Ticket ticket){
        //tripDate = ticket.getTrip().getTripDate();
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTripDate() {
        return tripDate;
    }

    public Date getTripDateTime() {
        return DateUtil.convertStringToDate(tripDate, TRIP_DATE_FORMAT);
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = DateUtil.convertDateTimeToString(tripDate, TRIP_DATE_FORMAT);;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public Date getTrainDateTime() {
        return DateUtil.convertTrainTimeToDate(trainTime);
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public void setTrainDateTime(Date trainDateTime){
        this.trainTime = DateUtil.convertTrainTimeToString(trainDateTime);
    }
}
