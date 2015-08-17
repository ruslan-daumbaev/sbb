package com.tsystems.sbb.controllers;

import com.tsystems.sbb.models.StationModel;
import com.tsystems.sbb.models.TrainModel;
import com.tsystems.sbb.models.TripDetailsModel;
import com.tsystems.sbb.models.TripModel;
import com.tsystems.sbb.services.contracts.StationsService;
import com.tsystems.sbb.services.contracts.TrainsService;
import com.tsystems.sbb.services.contracts.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by rdaumbae on 29.07.2015.
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    //private final Logger logger = LoggerFactory.logger(AdminController.class);

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private StationsService stationsService;

    @Autowired
    private TripsService tripsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "redirect:admin/trips";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trips")
    public String trips(Model uiModel){
        List<TripModel> trips = tripsService.getCurrentTrips();
        uiModel.addAttribute("trips", trips);
        return "admin/trips";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trip/{tripId}")
    public String trip(@PathVariable(value="tripId") int tripId, Model uiModel){
        TripDetailsModel tripDetailsModel = tripsService.getTripDetails(tripId);
        uiModel.addAttribute("tripDetailsModel", tripDetailsModel);
        return "admin/trip";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trains")
     public String trains(Model uiModel){
        List<TrainModel> trains = trainsService.getAllTrains();
        uiModel.addAttribute("trains", trains);
        return "admin/trains";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/train/{trainId}")
    public String train(@PathVariable(value="trainId") int trainId, Model uiModel){
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

    @RequestMapping(method = RequestMethod.GET, value = "/station/{stationId}")
    public String station(@PathVariable(value="stationId") int stationId, Model uiModel){
        StationModel model = stationsService.getStation(stationId);
        uiModel.addAttribute("stationModel", model);
        return "admin/station";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addStation")
    public String addStation(Model uiModel){
        StationModel model = new StationModel();;
        uiModel.addAttribute("stationModel", model);
        return "admin/station";
    }

    @RequestMapping(value = "/saveStation",
            method = RequestMethod.POST)
    public String saveStation(@ModelAttribute("stationModel") StationModel stationModel){

        stationsService.saveStation(stationModel);
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

        trainsService.saveTrain(trainModel);
        return "redirect:trains";
    }

    @RequestMapping(value = "/getTrain",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
         public @ResponseBody
         TrainModel getTrain(@RequestParam int trainId) {

        return trainsService.getTrain(trainId);
    }

    @RequestMapping(value = "/checkTrainNumber", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody boolean checkTrainNumber(@RequestParam String trainNumber) {
        return trainsService.checkTrainNumber(trainNumber);
    }

    @RequestMapping(value = "/stationSchedule/{stationId}", method = RequestMethod.GET)
    public String stationSchedule(@PathVariable(value="stationId")  int stationId, Model uiModel) {
        StationModel stationModel = stationsService.getStationSchedule(stationId);
        uiModel.addAttribute("stationModel", stationModel);
        return "admin/schedule";
    }
}
