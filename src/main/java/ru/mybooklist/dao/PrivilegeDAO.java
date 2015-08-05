package ru.mybooklist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mybooklist.model.Privilege;

import javax.transaction.Transactional;

/**
 * @author Daniyar Itegulov
 */
@Repository
@Transactional
public class PrivilegeDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public PrivilegeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addPrivilege(Privilege privilege) {
        currentSession().save(privilege);
    }

    public Privilege findByName(String name) {
        return (Privilege) currentSession().createCriteria(Privilege.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
    }
}
