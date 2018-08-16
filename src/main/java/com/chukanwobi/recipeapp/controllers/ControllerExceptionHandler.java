package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.exceptions.ImageNotFoundException;
import com.chukanwobi.recipeapp.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ImageNotFoundException.class)
    public void NullPointerExceptionHandler(){
        log.debug("image not loaded");
    }
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleNumberFormatException (Exception e)
    {
        log.error("A number format exception has occurred and its being handled");
        ModelAndView modelAndView = new ModelAndView();
        String message = "A number formation for exception has occurred " +e.getMessage().toLowerCase()+" \n Ids must be numbers only";

        modelAndView.addObject("numberFormatException",message);
        modelAndView.setViewName("error400");
        return modelAndView;

    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception exception){
        log.error("A 404 error has occurred and its being handled");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("error404");
        return modelAndView;
    }
}
