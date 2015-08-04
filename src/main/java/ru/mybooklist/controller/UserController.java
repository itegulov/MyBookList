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
//TODO: move all login and signUp action to separate controllers
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "sign_up", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "user/add_user";
    }

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

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String successUser(@ModelAttribute("authtoken") AuthToken authToken,
                              Model model) {
        model.addAttribute("authtoken", authToken);
        return "user/success";
    }

    @RequestMapping(value = "is_user_exists", method = RequestMethod.GET)
    public @ResponseBody boolean isUserExists(@RequestParam("name") String name) {
        return userService.isUsernameAvailable(name);
    }

    @RequestMapping(value = "sign_up", method = RequestMethod.POST)
    public String addUserFromForm(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirect,
                                  WebRequest request) {
        if (bindingResult.hasErrors()) {
            return "user/add_user";
        }
        User user = userService.registerUser(userDTO);
        try {
            eventPublisher.publishEvent(
                    new OnRegistrationCompleteEvent(user, request.getLocale(), request.getContextPath()));
        } catch (Exception e) {
            return "user/email_error";
        }

        return "redirect:/user/success";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam("token") String token) {
        AuthToken authToken = userService.getAuthenticationToken(token);
        if (authToken == null) {
            return "redirect:/user/bad_token";
        }

        User user = authToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((authToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            userService.deleteToken(authToken);
            return "redirect:/user/bad_token";
        }

        userService.confirmUser(user);
        userService.deleteToken(authToken);
        return "redirect:/";
    }
}
