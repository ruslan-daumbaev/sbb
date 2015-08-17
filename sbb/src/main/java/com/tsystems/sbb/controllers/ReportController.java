package com.tsystems.sbb.controllers;

import com.tsystems.sbb.models.ReportModel;
import com.tsystems.sbb.models.TicketModel;
import com.tsystems.sbb.services.contracts.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody ReportModel report(@RequestParam(value="dateFrom") String dateFrom,
                                      @RequestParam(value="dateTo")String dateTo) {
        return ticketsService.getTicketsByDate(dateFrom, dateTo);
    }

}
