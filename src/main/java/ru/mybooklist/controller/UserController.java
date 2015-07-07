package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.model.User;

import javax.validation.Valid;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "user/add_user";
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String successUser(User user, Model model) {
        model.addAttribute("user", user);
        return "user/success";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUserFromForm(@Valid @ModelAttribute("user") User user,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "user/add_user";
        }
        userDAO.addUser(user);
        redirect.addFlashAttribute("user", user);
        return "redirect:user/success";
    }
}
