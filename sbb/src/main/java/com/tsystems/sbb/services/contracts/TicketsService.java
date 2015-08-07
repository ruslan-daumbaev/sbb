package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.models.TicketModel;

public interface TicketsService {
    TicketModel getDataForTicket(int trainId);
}
