package com.tsystems.sbb.exceptions;

public class NoFreePlacesException extends TicketException {

    public NoFreePlacesException() {
        super("All places for train are reserved");
    }
}
