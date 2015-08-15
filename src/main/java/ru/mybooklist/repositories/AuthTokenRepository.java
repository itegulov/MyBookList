package ru.mybooklist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mybooklist.model.AuthToken;

/**
 * @author Daniyar Itegulov
 */
public interface AuthTokenRepository extends CrudRepository<AuthToken, Integer> {
    AuthToken findAuthTokenByToken(String token);
}
