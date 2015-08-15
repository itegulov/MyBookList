package ru.mybooklist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mybooklist.model.Role;

/**
 * @author Daniyar Itegulov
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findRoleByName(String name);
}
