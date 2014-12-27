package com.kodepelangi.model.dao;

import com.kodepelangi.entity.UserRole;

import java.util.List;

/**
 * @author rakateja on 12/27/14.
 */
public interface UserRoleInterface {

    public int create(UserRole userRole);

    public boolean delete(int id);

    public boolean update(UserRole userRole);

    public List<UserRole> findAllByUser(int idUser);
}
