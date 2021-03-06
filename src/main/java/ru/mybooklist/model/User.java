package ru.mybooklist.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

/**
 * @author Daniyar Itegulov
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "email"}))
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false, length = 128)
    private String name;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 256)
    private String email;

    @Column(name = "join_date", nullable = false)
    private Date joinDate;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<BookList> bookLists;

    public User() {
    }

    public User(String name, String password, String email, Date joinDate, Collection<Role> roles, boolean confirmed) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.joinDate = joinDate;
        this.roles = roles;
        this.confirmed = confirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<BookList> getBookLists() {
        return bookLists;
    }

    public void setBookLists(Collection<BookList> bookLists) {
        this.bookLists = bookLists;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<Book> getRandomBooks() {
        List<Book> books = new ArrayList<>();
        for (BookList bookList: bookLists) {
            books.addAll(bookList.getBooks());
        }
        Collections.shuffle(books);
        return books.subList(0, Math.min(books.size(), 3));
    }
}
