package com.tsystems.sbb.controllers;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.exceptions.PassenegerRegisteredException;
import com.tsystems.sbb.models.ScheduleModel;
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
        //return stationsService.getStationSchedule(stationId);
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

    @RequestMapping(value = "/buyTicket/{scheduleId}", method = RequestMethod.GET)
    public String buyTicket(@PathVariable(value="scheduleId") int scheduleId, Model uiModel){
        TicketModel ticketModel = ticketsService.getDataForTicket(scheduleId);
        if(ticketModel == null)
            return "redirect:index";
        uiModel.addAttribute("ticketModel", ticketModel);
        return "getticket";
    }

    @RequestMapping(value = "/buyTicket/{scheduleId}", method = RequestMethod.POST)
    public String confirmTicket(@PathVariable(value="scheduleId") int scheduleId,
                                @Valid @ModelAttribute("ticketModel") TicketModel ticketModel,
                                BindingResult result,
                                RedirectAttributes redirectAttributes){
       // try {
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", result);
            redirectAttributes.addFlashAttribute("ticketModel", ticketModel);
            //return "redirect:/buyTicket/"+ticketModel.getScheduleId();
            return "getticket";
        }
        try{
            ticketsService.confirmTicket(ticketModel);
        } catch (PassenegerRegisteredException e) {
            result.reject(e.getMessage());
            redirectAttributes.addFlashAttribute("errors", result);
            redirectAttributes.addFlashAttribute("ticketModel", ticketModel);
            //return "redirect:/buyTicket/"+ticketModel.getScheduleId();
            return "getticket";
        }
        return "redirect:/trains";
    }

}
