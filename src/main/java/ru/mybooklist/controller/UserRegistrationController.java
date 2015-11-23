package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mybooklist.event.OnRegistrationCompleteEvent;
import ru.mybooklist.model.AuthToken;
import ru.mybooklist.model.User;
import ru.mybooklist.model.dto.UserDTO;
import ru.mybooklist.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("user")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping(value = "sign_up", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "user/add_user";
    }

    @RequestMapping(value = "sign_up", method = RequestMethod.POST)
    public String addUserFromForm(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirect,
                                  HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "user/add_user";
        }
        User user = userService.registerUser(userDTO);
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
                    user, localeResolver.resolveLocale(request), request.getContextPath()));
        } catch (Exception e) {
            model.addAttribute("user", user);
            return "user/email_error";
        }
        redirect.addFlashAttribute("user", user);
        return "redirect:/user/success";
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String successUser(@ModelAttribute("user") User user,
                              Model model) {
        model.addAttribute("user", user);
        return "user/success";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam("token") String token, Model model,
                                      HttpServletRequest request) {
        AuthToken authToken = userService.getAuthenticationToken(token);
        if (authToken == null) {
            model.addAttribute("message", messageSource.getMessage("authToken.invalid", null,
                    localeResolver.resolveLocale(request)));
            return "user/bad_token";
        }

        User user = authToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((authToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            userService.deleteToken(authToken);
            model.addAttribute("message", messageSource.getMessage("authToken.expired", null,
                    localeResolver.resolveLocale(request)));
            return "user/bad_token";
        }

        userService.confirmUser(user);
        userService.deleteToken(authToken);
        return "redirect:/user/login";
    }
}
