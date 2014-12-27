package com.kodepelangi.model.dao;

import com.kodepelangi.entity.Role;

import java.util.List;

/**
 * @author rakateja on 12/25/14.
 */
public interface RoleInterface {
    public int add(Role role);

    public boolean update(Role user);

    public boolean delete(int id);

    public Role findById(int id);

    public List<Role> findAll();

}
