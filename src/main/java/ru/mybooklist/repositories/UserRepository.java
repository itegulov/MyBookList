package ru.mybooklist.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.mybooklist.model.User;

/**
 * @author Daniyar Itegulov
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByName(String name);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.name = ?1")
    boolean isNameExists(String name);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.email = ?1")
    boolean isEmailExists(String email);
}
