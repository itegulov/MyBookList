package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mybooklist.model.User;
import ru.mybooklist.repositories.UserRepository;

import java.security.Principal;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping(value = "profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "booklists/{username}")
    public String getUserBookLists(@PathVariable String username, Model model, Principal principal) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            model.addAttribute("username", username);
            return "profile/no_user";
        }
        model.addAttribute("user", user);
        model.addAttribute("booklists", user.getBookLists());
        if (principal != null && principal.getName().equals(username)) {
            return "profile/full_booklists";
        } else {
            return "profile/booklists";
        }
    }

    @RequestMapping(value = "settings/{username}")
    public String getUserSettings(@PathVariable String username, Model model, Principal principal) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            model.addAttribute("username", username);
            return "profile/no_user";
        }
        if (!principal.getName().equals(username)) {
            model.addAttribute("username", username);
            return "profile/no_access";
        }
        model.addAttribute("user", user);
        return "profile/settings";
    }

    @RequestMapping(value = "{username}")
    public String getUserProfile(@PathVariable String username,
                             Principal principal, Model model) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            model.addAttribute("username", username);
            return "profile/no_user";
        }
        model.addAttribute("user", user);
        model.addAttribute("randomBooks", user.getRandomBooks());
        if (principal != null && principal.getName().equals(username)) {
            return "profile/full_info";
        } else {
            return "profile/info";
        }
    }
}
