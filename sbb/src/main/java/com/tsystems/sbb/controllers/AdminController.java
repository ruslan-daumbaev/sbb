package com.tsystems.sbb.controllers;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.models.TrainModel;
import com.tsystems.sbb.services.contracts.StationsService;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by rdaumbae on 29.07.2015.
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    //private final Logger logger = LoggerFactory.logger(AdminController.class);

    private TrainsService trainsService;
    private StationsService stationsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel){
        List<TrainModel> trains = trainsService.getAllTrains();
        uiModel.addAttribute("trains", trains);
        return "admin/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trains")
     public String trains(Model uiModel){
        List<TrainModel> trains = trainsService.getAllTrains();
        uiModel.addAttribute("trains", trains);
        return "admin/trains";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/train")
    public String train(int trainId, Model uiModel){
        TrainModel model = trainsService.getTrain(trainId);
        uiModel.addAttribute("trainModel", model);
        return "admin/train";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addTrain")
    public String addTrain(Model uiModel){
        TrainModel model = trainsService.getTrain(0);
        uiModel.addAttribute("trainModel", model);
        return "admin/train";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stations")
    public String stations(Model uiModel){
        List<StationModel> stations = stationsService.getAllStations();
        uiModel.addAttribute("stations", stations);
        return "admin/stations";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/station")
    public String station(int stationId, Model uiModel){
        StationModel model = stationsService.getStation(stationId);
        uiModel.addAttribute("stationModel", model);
        return "admin/station";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addStation")
    public String addStation(Model uiModel){
        StationModel model = stationsService.getStation(0);
        uiModel.addAttribute("stationModel", model);
        return "admin/station";
    }

    @RequestMapping(value = "/saveStation",
            method = RequestMethod.POST)
    public String saveStation(@ModelAttribute("stationModel") StationModel stationModel){

        stationsService.addStation(stationModel);
        return "redirect:stations";
    }

    @RequestMapping(value = "/stationsJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody Collection<StationModel> stationsJson() {

        return stationsService.getAllStations();
    }

    @RequestMapping(value = "/trainsJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody Collection<TrainModel> trainsJson() {

        return trainsService.getAllTrains();
    }

    @RequestMapping(value = "/saveTrain",
            method = RequestMethod.POST)
    public String saveTrain(@ModelAttribute("trainModel") TrainModel trainModel){

        trainsService.addTrain(trainModel);
        return "redirect:trains";
    }

    @RequestMapping(value = "/getTrain",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody
    TrainModel getTrain(@RequestParam int trainId) {

        return trainsService.getTrain(trainId);
    }

    @RequestMapping(value = "/stationSchedule", method = RequestMethod.GET)
    public String stationSchedule(@RequestParam int stationId, Model uiModel) {
        StationModel stationModel = stationsService.getStationSchedule(stationId);
        uiModel.addAttribute("stationModel", stationModel);
        return "admin/schedule";
    }

    @Autowired
    public void setTrainsService(TrainsService trainsService) {

        this.trainsService = trainsService;
    }

    @Autowired
    public void setStationsService(StationsService stationsService) {

        this.stationsService = stationsService;
    }
}
