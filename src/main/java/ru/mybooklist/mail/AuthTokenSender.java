package ru.mybooklist.mail;

import ru.mybooklist.model.AuthToken;

import javax.mail.MessagingException;

/**
 * @author Daniyar Itegulov
 */
public enum AuthTokenSender {
    ;
    public static void sendAuthToken(AuthToken token) throws MessagingException {
        Mailer.send(token.getEmail(), "Confirm your registration at MyBookList",
                "http://localhost:8080/user/confirm?token=" + token.getToken());
    }
}
