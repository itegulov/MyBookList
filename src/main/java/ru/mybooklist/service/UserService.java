package ru.mybooklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mybooklist.dao.AuthDAO;
import ru.mybooklist.dao.RoleDAO;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.model.AuthToken;
import ru.mybooklist.model.User;
import ru.mybooklist.model.dto.UserDTO;

import java.util.Date;

/**
 * @author Daniyar Itegulov
 */
@Service
public class UserService {
    @Autowired
    private AuthDAO authDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return userDAO.isUsernameAvailable(username);
    }

    public boolean isEmailAvailable(String email) {
        return userDAO.isEmailAvailable(email);
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getEmail(), roleDAO.getRole("user"), false);
        userDAO.addUser(user);
        return user;
    }

    public void createAuthenticationToken(User user, String token) {
        AuthToken authToken = new AuthToken();
        authToken.setTimestamp(new Date());
        authToken.setUser(user);
        authToken.setToken(token);
        authDAO.addToken(authToken);
    }

    public AuthToken getAuthenticationToken(String token) {
        return authDAO.getByToken(token);
    }

    public void confirmUser(User user) {
        user.setConfirmed(true);
        userDAO.updateUser(user);
    }

    public void deleteToken(AuthToken authToken) {
        authDAO.deleteToken(authToken);
    }
}
