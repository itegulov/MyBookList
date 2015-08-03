package ru.mybooklist.mail;

import ru.mybooklist.model.AuthToken;

import javax.mail.MessagingException;

/**
 * @author Daniyar Itegulov
 */
public enum AuthTokenSender {
    ;
    public static void sendAuthToken(String email, AuthToken token) throws MessagingException {
        Mailer.send(email, "Confirm your registration at MyBookList",
                "http://localhost:8080/user/confirm?token=" + token.getToken());
    }
}
