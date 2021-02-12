package com.keita.schoolmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleID;
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userRoleID")
    private Authenticate authUser;

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Authenticate getAuthUser() {
        return authUser;
    }

    public void setAuthUser(Authenticate authUser) {
        this.authUser = authUser;
    }
}
