package ru.mybooklist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SQLCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mybooklist.model.AuthToken;

import java.util.List;

/**
 * @author Daniyar Itegulov
 */
@Repository
@Transactional
public class AuthDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public AuthDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addToken(AuthToken token) {
        currentSession().save(token);
    }

    public void deleteToken(AuthToken token) {
        currentSession().delete(token);
    }

    public AuthToken getByToken(String token) {
        List<AuthToken> list = currentSession().createCriteria(AuthToken.class)
                .add(Restrictions.eq("token", token)).list();
        assert list.size() == 1;
        return list.get(0);
    }
}