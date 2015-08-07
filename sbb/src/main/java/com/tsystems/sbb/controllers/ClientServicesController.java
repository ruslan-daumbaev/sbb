package com.tsystems.sbb.controllers;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.models.TicketModel;
import com.tsystems.sbb.models.TrainModel;
import com.tsystems.sbb.services.contracts.StationsService;
import com.tsystems.sbb.services.contracts.TicketsService;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
@Controller
public class ClientServicesController {

    private TrainsService trainsService;
    private StationsService stationsService;
    private TicketsService ticketsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/trains", method = RequestMethod.GET)
    public String trains(){
        return "trains";
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String schedule(Model uiModel){
        List<StationModel> stationModels = stationsService.getAllStations();
        uiModel.addAttribute("stationModels", stationModels);
        return "schedule";
    }

    @RequestMapping(value = "/scheduleJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody
    StationModel scheduleJson(@RequestParam int stationId) {
        return stationsService.getStationSchedule(stationId);
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String tickets(){
        return "tickets";
    }

    @RequestMapping(value = "/buyTicket", method = RequestMethod.GET)
    public String buyTicket(@RequestParam int trainId, @RequestParam int stationId, Model uiModel){
        TicketModel ticketModel = ticketsService.getDataForTicket(trainId);
        uiModel.addAttribute("ticketModel", ticketModel);
        return "getticket";
    }

    @RequestMapping(value = "/confirmTicket", method = RequestMethod.POST)
    public String confirmTicket(){

        return "trains";
    }

    @Autowired
    public void setTrainsService(TrainsService trainsService) {
        this.trainsService = trainsService;
    }

    @Autowired
    public void setTicketsService(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @Autowired
    public void setStationsService(StationsService stationsService) {
        this.stationsService = stationsService;
    }
}
