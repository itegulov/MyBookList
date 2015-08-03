package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.dto.UserDTO;
import ru.mybooklist.model.AuthToken;
import ru.mybooklist.service.AuthTokenService;

import javax.validation.Valid;

/**
 * @author Daniyar Itegulov
 */
//TODO: move all login and signUp action to separate controllers
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AuthTokenService authTokenService;

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
        return userDAO.isUsernameAvailable(name);
    }

    @RequestMapping(value = "sign_up", method = RequestMethod.POST)
    public String addUserFromForm(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "user/add_user";
        }
        AuthToken token = authTokenService.sendToken(userDTO);
        redirect.addFlashAttribute("authtoken", token);
        return "redirect:/user/success";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam("token") String token) {
        authTokenService.confirmRegistration(token);
        return "redirect:/";
    }
}
