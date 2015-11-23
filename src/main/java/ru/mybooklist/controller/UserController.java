package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mybooklist.service.UserService;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "is_user_exists", method = RequestMethod.GET)
    public @ResponseBody boolean isUserExists(@RequestParam("name") String name) {
        return userService.isUsernameAvailable(name);
    }
}
