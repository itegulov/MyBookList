package ru.mybooklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Daniyar Itegulov
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
}
