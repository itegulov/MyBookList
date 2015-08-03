package ru.mybooklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mybooklist.dao.AuthDAO;
import ru.mybooklist.dao.RoleDAO;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.dto.UserDTO;
import ru.mybooklist.mail.AuthTokenSender;
import ru.mybooklist.model.AuthToken;
import ru.mybooklist.model.User;

import javax.mail.MessagingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * @author Daniyar Itegulov
 */
@Service("authTokenService")
public class AuthTokenService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AuthDAO authDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static Random rnd = new SecureRandom();

    public void confirmRegistration(String token) {
        AuthToken authToken = authDAO.getByToken(token);
        authToken.getUser().setConfirmed(true);
        authDAO.deleteToken(authToken);
        userDAO.updateUser(authToken.getUser());
    }

    public AuthToken sendToken(UserDTO userDTO) {
        User user = new User(userDTO, roleDAO.getRole("user"), passwordEncoder);
        userDAO.addUser(user);
        AuthToken token = new AuthToken();
        token.setTimestamp(new Date());
        token.setToken(new BigInteger(130, rnd).toString(36));
        token.setUser(user);
        authDAO.addToken(token);
        try {
            AuthTokenSender.sendAuthToken(user.getEmail(), token);
        } catch (MessagingException e) {
            System.err.println("Couldn't send a mail: " + e);
            throw new IllegalStateException(e);
        }
        return token;
    }
}
