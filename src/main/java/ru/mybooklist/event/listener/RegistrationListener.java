package ru.mybooklist.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.mybooklist.event.OnRegistrationCompleteEvent;
import ru.mybooklist.model.User;
import ru.mybooklist.service.UserService;

import java.util.UUID;

/**
 * @author Daniyar Itegulov
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createAuthenticationToken(user, token);

        String confirmationUrl = event.getAppUrl() + "/user/confirm?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Registration Confirmation");
        email.setText(message + " " + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
