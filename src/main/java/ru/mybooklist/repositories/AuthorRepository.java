package ru.mybooklist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mybooklist.model.Author;

/**
 * @author Daniyar Itegulov
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
