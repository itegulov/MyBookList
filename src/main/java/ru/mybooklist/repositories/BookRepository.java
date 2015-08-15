package ru.mybooklist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mybooklist.model.Book;

/**
 * @author Daniyar Itegulov
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
}
