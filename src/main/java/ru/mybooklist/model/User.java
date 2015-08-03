package ru.mybooklist.model;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mybooklist.dto.UserDTO;

import javax.persistence.*;

/**
 * @author Daniyar Itegulov
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "email"}))
public class User {
    @Id @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToOne()
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed = false;

    public User() {
    }

    public User(UserDTO userDTO, Role role, PasswordEncoder passwordEncoder) {
        name = userDTO.getUsername();
        password = passwordEncoder.encode(userDTO.getPassword());
        email = userDTO.getEmail();
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
