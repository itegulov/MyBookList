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

    @RequestMapping(value = "/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }

    @RequestMapping(value = "/contacts")
    public ModelAndView contacts() {
        return new ModelAndView("contacts");
    }
}
