package com.keita.schoolmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Authenticate")
public class Authenticate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authID;
    @NotBlank(message = "Please enter a valid username")
    private String username;
    //    @NotBlank(message = "Enter a role for the user")
    private String userRole;
    @NotBlank(message = "Please enter a valid password")
    @Size(min = 6, message = "Password must be 6 digit long")
    private String password;
    private String conformPassword;

    public Authenticate() {
    }

    ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userAuthID", referencedColumnName = "userID")
    private User user;

//    @OneToMany( fetch = FetchType.LAZY, mappedBy = "authUser")
//    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String role) {
        this.userRole = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConformPassword() {
        return conformPassword;
    }

    public void setConformPassword(String conformP) {
        this.conformPassword = conformP;
    }

//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

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
