package ru.mybooklist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mybooklist.model.User;

/**
 * @author Daniyar Itegulov
 */
@Repository
@Transactional
public class UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addUser(User user) {
        currentSession().save(user);
    }

    public User getUserById(int id) {
        return (User) currentSession().get(User.class, id);
    }
}
