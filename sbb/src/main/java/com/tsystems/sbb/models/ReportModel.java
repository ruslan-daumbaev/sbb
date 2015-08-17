package com.tsystems.sbb.models;

import java.util.ArrayList;
import java.util.List;

public class ReportModel {
    private List<TicketReportModel> tickets;

    public ReportModel(){
        tickets = new ArrayList<>();
    }

    public List<TicketReportModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketReportModel> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(TicketReportModel ticketReportModel){
        tickets.add(ticketReportModel);
    }
}
