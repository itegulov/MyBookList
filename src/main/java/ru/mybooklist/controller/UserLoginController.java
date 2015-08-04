package ru.mybooklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping(value = "user")
public class UserLoginController {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginUser() {
        return "user/login";
    }

    @RequestMapping(value = "loginfailed", method = RequestMethod.GET)
    public String loginFailedUser(Model model) {
        model.addAttribute("error", "true");
        return "forward:/user/login";
    }
}
