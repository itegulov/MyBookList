package ru.mybooklist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mybooklist.model.Author;

/**
 * @author Daniyar Itegulov
 */
@Repository
@Transactional
public class AuthorDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addAuthor(Author author) {
        currentSession().save(author);
    }

}