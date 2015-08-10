package ru.mybooklist.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mybooklist.dao.PrivilegeDAO;
import ru.mybooklist.dao.RoleDAO;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.model.Privilege;
import ru.mybooklist.model.Role;
import ru.mybooklist.model.User;

import java.util.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Daniyar Itegulov
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private PrivilegeDAO privilegeDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
        Role userRole = createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));

        if (userDAO.isUsernameAvailable("admin")) {
            userDAO.addUser(new User("admin", passwordEncoder.encode("admin"),
                    "admin@mybooklist.ru", new Date(), Arrays.asList(adminRole, userRole), true));
        }
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeDAO.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeDAO.addPrivilege(privilege);
        }
        return privilege;
    }

    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleDAO.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleDAO.addRole(role);
        }
        return role;
    }
}
