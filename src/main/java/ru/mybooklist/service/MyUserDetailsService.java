package ru.mybooklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.model.User;

import java.util.Collections;

/**
 * @author Daniyar Itegulov
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + s);
        } else if (!user.isConfirmed()) {
            throw new UsernameNotFoundException("User " + s + " isn't confirmed");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                true, true, true, true, Collections.singletonList(user.getRole()));
    }
}
