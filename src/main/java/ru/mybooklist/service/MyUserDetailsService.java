package ru.mybooklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mybooklist.model.Privilege;
import ru.mybooklist.model.Role;
import ru.mybooklist.model.User;
import ru.mybooklist.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Daniyar Itegulov
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked(request.getRemoteAddr())) {
            throw new IllegalStateException("Address is blocked");
        }
        User user = userRepository.findUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + s);
        } else if (!user.isConfirmed()) {
            throw new UsernameNotFoundException("User " + s + " isn't confirmed");
        }
        List<Privilege> privileges = user.getRoles().stream().flatMap(new Function<Role, Stream<Privilege>>() {
            @Override
            public Stream<Privilege> apply(Role role) {
                return role.getPrivileges().stream();
            }
        }).collect(Collectors.<Privilege>toList());
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                user.isConfirmed(), true, true, true, privileges);
    }
}
