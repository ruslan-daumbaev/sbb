package com.tsystems.sbb.services.contracts;

import com.tsystems.sbb.exceptions.TicketException;
import com.tsystems.sbb.models.ReportModel;
import com.tsystems.sbb.models.TicketModel;

import java.util.List;

public interface TicketsService {

    TicketModel getDataForTicket(int scheduleId);

    void confirmTicket(TicketModel ticketModel) throws TicketException;

    ReportModel getTicketsByDate(String fromDate, String toDate);
}
