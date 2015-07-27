package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mybooklist.dao.AuthDAO;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.mail.AuthTokenSender;
import ru.mybooklist.model.AuthToken;
import ru.mybooklist.model.User;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("user")
public class UserController {
    private Random rnd = new SecureRandom();

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AuthDAO authDAO;

    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("authtoken", new AuthToken());
        return "user/add_user";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginUser() {
        return "user/login";
    }

    @RequestMapping(value = "loginfailed", method = RequestMethod.GET)
    public String loginFailedUser() {
        return "user/loginfailed";
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
        return userDAO.isUsernameAvailable(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUserFromForm(@Valid @ModelAttribute("authtoken") AuthToken token,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "user/add_user";
        }
        token.setTimestamp(new Date());
        token.setToken(new BigInteger(130, rnd).toString(36));
        authDAO.addToken(token);
        try {
            AuthTokenSender.sendAuthToken(token);
        } catch (MessagingException e) {
            System.err.println("Couldn't send a mail: " + e);
        }
        redirect.addFlashAttribute("authtoken", token);
        return "redirect:user/success";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam("token") String token) {
        AuthToken authToken = authDAO.getByToken(token);
        User user = new User(authToken);
        authDAO.deleteToken(authToken);
        userDAO.addUser(user);
        return "redirect:/";
    }
}
