package ru.mybooklist.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author Daniyar Itegulov
 */
@Entity
@Table(name = "authtokens")
public class AuthToken {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "token")
    private String token;

    @Pattern(message = "{Pattern.user.name}", regexp = "^[a-zA-Z0-9]+$")
    @Length(message = "{Length.user.name}", min = 3, max = 20)
    @Column(name = "name")
    private String name;

    @Length(message = "{Length.user.password}", min = 6, max = 20)
    @Column(name = "password")
    private String password;

    @Email(message = "{Email.user.email}")
    @Column(name = "email")
    private String email;

    @Column(name = "timestamp")
    private Date timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthToken authToken = (AuthToken) o;

        return id == authToken.id;
    }

    @Override
    public int hashCode() {
        return id * 91;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
