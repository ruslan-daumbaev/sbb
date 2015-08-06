package com.tsystems.sbb.controllers;

import com.tsystems.sbb.entities.Train;
import com.tsystems.sbb.services.contracts.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by rdaumbae on 30.07.2015.
 */
@Controller
public class ClientServicesController {

    private TrainsService trainsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/trains", method = RequestMethod.GET)
    public String trains(){
        return "trains";
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String schedule(){
        return "schedule";
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String tickets(){
        return "tickets";
    }

    @Autowired
    public void setTrainsService(TrainsService trainsService) {
        this.trainsService = trainsService;
    }
}
