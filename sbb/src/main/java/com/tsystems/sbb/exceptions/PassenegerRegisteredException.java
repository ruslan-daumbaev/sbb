package com.tsystems.sbb.exceptions;

public class PassenegerRegisteredException extends TicketException  {

    public PassenegerRegisteredException(){
        super("Passenger with provided info has been already registered for this trip");
    }
}
