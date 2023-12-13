package com.temp.webshop.authentication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleid;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    //private String authority;

    public Role() {}

    /**
     * Tilldelar roll till User - ev. user.getId() != adminId?
     */
    public Role(UserRole userRole, User user) {
        this.userRole = userRole;
        this.user = user;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    /*public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }*/

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleid +
                ", userRole=" + userRole +
                ", customer=" + user +
                '}';
    }
}
