package ru.mybooklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mybooklist.dao.UserDAO;

/**
 * @author Daniyar Itegulov
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public boolean isUsernameAvailable(String username) {
        return userDAO.isUsernameAvailable(username);
    }

    public boolean isEmailAvailable(String email) {
        return userDAO.isEmailAvailable(email);
    }
}
