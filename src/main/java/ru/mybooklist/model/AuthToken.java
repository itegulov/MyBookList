package ru.mybooklist.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mybooklist.dto.UserDTO;

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
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public AuthToken() {

    }

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
