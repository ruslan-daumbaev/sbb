package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.exceptions.TicketException;
import com.tsystems.sbb.models.TicketModel;

public interface TicketsService {

    TicketModel getDataForTicket(int scheduleId);

    void confirmTicket(TicketModel ticketModel) throws TicketException;
}
