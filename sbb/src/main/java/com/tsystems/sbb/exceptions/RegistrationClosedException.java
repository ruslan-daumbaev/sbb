package com.tsystems.sbb.exceptions;

public class RegistrationClosedException extends TicketException {
    public RegistrationClosedException() {
        super("Registration for trip is closed");
    }
}
