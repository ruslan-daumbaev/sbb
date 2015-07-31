package com.tsystems.sbb.controllers;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel){
        List<Train> trains = trainsService.getAllTrains();
        uiModel.addAttribute("trains", trains);
        return "admin/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trains")
     public String trains(Model uiModel){
        List<Train> trains = trainsService.getAllTrains();
        uiModel.addAttribute("trains", trains);
        return "admin/trains";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stations")
    public String stations(Model uiModel){
        return "admin/stations";
    }

    @Autowired
    public void setTrainsService(TrainsService trainsService) {
        this.trainsService = trainsService;
    }
}
