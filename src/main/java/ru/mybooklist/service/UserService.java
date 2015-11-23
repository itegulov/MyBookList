package ru.mybooklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mybooklist.model.AuthToken;
import ru.mybooklist.model.User;
import ru.mybooklist.model.dto.UserDTO;
import ru.mybooklist.repositories.AuthTokenRepository;
import ru.mybooklist.repositories.RoleRepository;
import ru.mybooklist.repositories.UserRepository;

import java.util.Collections;
import java.util.Date;

/**
 * @author Daniyar Itegulov
 */
@Service
public class UserService {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameAvailable(String username) {
        return !userRepository.isNameExists(username);
    }

    public boolean isEmailAvailable(String email) {
        return !userRepository.isEmailExists(email);
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getEmail(), new Date(), Collections.singletonList(roleRepository.findRoleByName("user")), false);
        userRepository.save(user);
        return user;
    }

    public void createAuthenticationToken(User user, String token) {
        AuthToken authToken = new AuthToken();
        authToken.setTimestamp(new Date());
        authToken.setUser(user);
        authToken.setToken(token);
        authTokenRepository.save(authToken);
    }

    public AuthToken getAuthenticationToken(String token) {
        return authTokenRepository.findAuthTokenByToken(token);
    }

    public void confirmUser(User user) {
        user.setConfirmed(true);
        userRepository.save(user);
    }

    public void deleteToken(AuthToken authToken) {
        authTokenRepository.delete(authToken);
    }
}
