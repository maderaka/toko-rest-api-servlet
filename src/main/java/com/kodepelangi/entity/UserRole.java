package com.kodepelangi.entity;

import java.util.Date;

/**
 * @author rakateja on 12/27/14.
 */
public class UserRole {
    private User user;
    private Role role;
    private Date createdAt;

    public UserRole(){
        this.setRole(new Role());
        this.setUser(new User());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
