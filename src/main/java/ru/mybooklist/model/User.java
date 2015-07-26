package ru.mybooklist.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * @author Daniyar Itegulov
 */
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Pattern(message = "{Pattern.user.name}", regexp="^[a-zA-Z0-9]+$")
    @Length(message = "{Length.user.name}", min = 3, max=20)
    @Column(name = "name")
    private String name;

    @Length(message = "{Length.user.password}", min = 6, max=20)
    @Column(name = "password")
    private String password;

    @Email(message = "{Email.user.email}")
    @Column(name = "email")
    private String email;

    public User(AuthToken authToken) {
        name = authToken.getName();
        password = authToken.getPassword();
        email = authToken.getEmail();
    }

    public User() {
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
}
