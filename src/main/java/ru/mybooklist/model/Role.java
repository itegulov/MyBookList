package ru.mybooklist.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Daniyar Itegulov
 */
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Role implements GrantedAuthority {
    @Id @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @Override
    public String getAuthority() {
        return null;
    }
}
