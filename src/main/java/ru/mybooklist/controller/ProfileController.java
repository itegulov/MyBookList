package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.model.User;

import java.security.Principal;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping(value = "profile")
public class ProfileController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "booklists")
    public String getBookLists(Model model, Principal principal) {
        User user = userDAO.getUserByUsername(principal.getName());
        model.addAttribute("booklists", user.getBookLists());
        return "profile/booklists";
    }
}
