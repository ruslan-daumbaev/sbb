package com.tsystems.sbb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rdaumbae on 29.07.2015.
 */
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
