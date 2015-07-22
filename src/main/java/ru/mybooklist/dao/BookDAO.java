package ru.mybooklist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mybooklist.model.Book;
import ru.mybooklist.model.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Daniyar Itegulov
 */
@Repository
@Transactional
public class BookDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addBook(Book book) {
        currentSession().save(book);
    }

    @SuppressWarnings("unchecked")
    public List<Book> allBooks() {
        return currentSession().createCriteria(Book.class).list();
    }

    @SuppressWarnings("unchecked")
    public List<Book> popularBooks() {
        return currentSession().createCriteria(Book.class).list();
    }

    public Book getBookById(int id) {
        return (Book) currentSession().get(Book.class, id);
    }
}
