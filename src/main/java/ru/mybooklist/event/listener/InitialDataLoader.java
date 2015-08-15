package ru.mybooklist.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mybooklist.model.Privilege;
import ru.mybooklist.model.Role;
import ru.mybooklist.model.User;
import ru.mybooklist.repositories.PrivilegeRepository;
import ru.mybooklist.repositories.RoleRepository;
import ru.mybooklist.repositories.UserRepository;

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
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
        Role userRole = createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));

        if (!userRepository.isNameExists("admin")) {
            userRepository.save(new User("admin", passwordEncoder.encode("admin"),
                    "admin@mybooklist.ru", new Date(), Arrays.asList(adminRole, userRole), true));
        }
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findPrivilegeByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findRoleByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
