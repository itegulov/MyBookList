package ru.mybooklist.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Daniyar Itegulov
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return null;
    }
}
