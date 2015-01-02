package com.kodepelangi.model.dao;

import com.kodepelangi.entity.UserRole;

import java.util.List;

/**
 * @author rakateja on 12/27/14.
 */
public interface UserRoleInterface {

    static final String TABLE_NAME = "user_has_roles";

    public int create(UserRole userRole);

    public boolean delete(int id);

    public boolean update(UserRole userRole);

    public UserRole findById(int id);

    public List<UserRole> findByUser(int idUser);

    public List<UserRole> findByRole(int idRole);
}
