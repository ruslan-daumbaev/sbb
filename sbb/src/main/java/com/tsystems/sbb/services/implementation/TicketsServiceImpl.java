package com.tsystems.sbb.services.implementation;

import com.tsystems.sbb.models.TicketModel;
import com.tsystems.sbb.services.contracts.TicketsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("ticketsService")
public class TicketsServiceImpl implements TicketsService {

    public TicketModel getDataForTicket(int trainId) {
        TicketModel ticketModel = new TicketModel();
        ticketModel.setBirthDate(new Date());
        ticketModel.setLastName("Test");
        ticketModel.setFirstName("Test");
        ticketModel.setTrainNumber("A1");
        ticketModel.setStationName("Station 1");
        return ticketModel;
    }
}
