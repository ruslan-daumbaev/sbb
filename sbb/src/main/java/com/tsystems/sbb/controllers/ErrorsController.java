package com.tsystems.sbb.controllers;

import com.tsystems.sbb.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class ErrorsController {

    private static Logger log = LogManager.getLogger(ErrorsController.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        log.error("Resource not found");
        return "service/notfound";
    }

    @ExceptionHandler(Exception.class)
    public String handleAllException(Exception ex) {
        log.error("Exception", ex);
        return "service/error";
    }
}
