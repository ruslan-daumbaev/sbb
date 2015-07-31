package com.tsystems.sbb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * Created by rdaumbae on 29.07.2015.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }

    @RequestMapping("/loginFail")
    public String loginFail(Model uiModel, Locale locale) {
        //logger.info("Login failed detected");
        uiModel.addAttribute("message", "Invalid authentication data");
        return "login";
    }

}
