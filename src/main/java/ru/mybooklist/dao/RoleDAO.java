package ru.mybooklist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mybooklist.model.Role;

/**
 * @author Daniyar Itegulov
 */
@Repository
@Transactional
public class RoleDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public RoleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Role getRole(String roleName) {
        return (Role) currentSession().createCriteria(Role.class)
                .add(Restrictions.eq("name", roleName)).uniqueResult();
    }
}
