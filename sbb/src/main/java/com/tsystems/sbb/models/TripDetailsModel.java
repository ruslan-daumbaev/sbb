package com.tsystems.sbb.models;

import java.util.ArrayList;
import java.util.List;

public class TripDetailsModel extends TripModel {
    private List<PassengerModel> passengers;
    private int placesAmount;

    public TripDetailsModel(){
        passengers = new ArrayList<PassengerModel>();
    }

    public List<PassengerModel> getPassengers(){
        return passengers;
    }

    public void addPassenger(PassengerModel passenger){
        passengers.add(passenger);
    }

    public int getPlacesAmount() {
        return placesAmount;
    }

    public void setPlacesAmount(int placesAmount) {
        this.placesAmount = placesAmount;
    }

    public int getReservedPlacesAmount() {
        return passengers.size();
    }
}
