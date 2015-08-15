package ru.mybooklist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mybooklist.model.Privilege;

/**
 * @author Daniyar Itegulov
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {
    Privilege findPrivilegeByName(String name);
}
