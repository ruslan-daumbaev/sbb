package com.tsystems.sbb.models;

import com.tsystems.sbb.entities.Passenger;
import com.tsystems.sbb.entities.Ticket;
import com.tsystems.sbb.utils.DateUtil;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PassengerModel {
    @NotNull
    @NotEmpty(message = "First name can not be empty")
    private String firstName;
    @NotNull
    @NotEmpty(message = "Last name can not be empty")
    private String lastName;
    @NotNull
    @NotEmpty(message = "Birth date can not be empty")
    private String birthDateString;
    private Date registrationDate;

    public PassengerModel(){}

    public PassengerModel(Ticket ticket){
        Passenger passenger = ticket.getPassenger();
        if(passenger != null){
            firstName = passenger.getFirstName();
            lastName = passenger.getLastName();
            birthDateString = DateUtil.convertDateTimeToString(passenger.getBirthDate(), "dd.MM.yyyy");
        }
        registrationDate = ticket.getInsDate();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return DateUtil.convertStringToDate(birthDateString, "dd/MM/yyyy");
    }


    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
