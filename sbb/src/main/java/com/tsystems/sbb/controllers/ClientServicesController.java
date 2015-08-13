package com.tsystems.sbb.controllers;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.exceptions.PassenegerRegisteredException;
import com.tsystems.sbb.exceptions.TicketException;
import com.tsystems.sbb.models.*;
import com.tsystems.sbb.services.contracts.StationsService;
import com.tsystems.sbb.services.contracts.TicketsService;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
@Controller
public class ClientServicesController {

    @Autowired
    private TrainsService trainsService;
    @Autowired
    private StationsService stationsService;
    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/trains", method = RequestMethod.GET)
    public String trains(Model uiModel){
        List<StationModel> stationModels = stationsService.getAllStations();
        uiModel.addAttribute("stationModels", stationModels);
        return "trains";
    }

    @RequestMapping(value = "/findTrainsJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody
    List<ScheduleModel> findTrainsJson(@RequestParam int stationFirstId, @RequestParam  int stationSecondId,
                                       @RequestParam  String timeFrom, @RequestParam String timeTo) {
        return trainsService.findTrainsByParams(stationFirstId, stationSecondId, timeFrom, timeTo);
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

    @RequestMapping(value = "/getTicketData/{scheduleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TicketModel getTicketData(@PathVariable(value = "scheduleId") int scheduleId){
        return ticketsService.getDataForTicket(scheduleId);
    }

    @RequestMapping(value = "/buyTicket/{scheduleId}", method = RequestMethod.GET)
    public String buyTicket(@PathVariable(value="scheduleId") int scheduleId, Model uiModel){
        TicketModel ticketModel = ticketsService.getDataForTicket(scheduleId);
        if(ticketModel == null)
            return "redirect:index";
        uiModel.addAttribute("ticketModel", ticketModel);
        return "getticket";
    }

    @RequestMapping(value = "/confirmTicket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ConfirmTicketResult confirmTicket(@Valid @ModelAttribute("ticketModel") TicketModel ticketModel,
                                BindingResult result){
        ConfirmTicketResult confirmResult = new ConfirmTicketResult();
        confirmResult.setIsOk(true);
        if(result.hasErrors()){
            confirmResult.setErrorMessage(result.toString());
            confirmResult.setIsOk(false);
        }
        else{
            try{
                ticketsService.confirmTicket(ticketModel);
            } catch (TicketException e) {
                confirmResult.setErrorMessage(e.getMessage());
                confirmResult.setIsOk(false);
            }
        }

        return confirmResult;
    }

}
