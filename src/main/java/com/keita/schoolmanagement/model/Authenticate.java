package com.keita.schoolmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "Authenticate")
public class Authenticate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authID;
    @NotBlank(message = "Please enter a valid username")
    private String username;
    @NotBlank(message = "Please enter a valid password")
    private String password;

    public Authenticate(){};

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "authUser")
    private Set<Role> roles;

    public Long getUserID() {
        return authID;
    }

    public void setUserID(Long id) {
        this.authID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getAuthID() {
        return authID;
    }

    public void setAuthID(Long authID) {
        this.authID = authID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
