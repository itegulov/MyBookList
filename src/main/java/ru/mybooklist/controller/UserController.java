package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.event.OnRegistrationCompleteEvent;
import ru.mybooklist.model.User;
import ru.mybooklist.model.dto.UserDTO;
import ru.mybooklist.model.AuthToken;
import ru.mybooklist.service.UserService;

import javax.validation.Valid;
import java.util.Calendar;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginUser() {
        return "user/login";
    }

    @RequestMapping(value = "loginfailed", method = RequestMethod.GET)
    public String loginFailedUser(Model model) {
        model.addAttribute("error", "true");
        return "forward:/user/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutUser() {
        return "user/logout";
    }

    @RequestMapping(value = "is_user_exists", method = RequestMethod.GET)
    public @ResponseBody boolean isUserExists(@RequestParam("name") String name) {
        return userService.isUsernameAvailable(name);
    }
}
