package com.kodepelangi.model.dao;

import com.kodepelangi.entity.UserRole;

import java.util.List;

/**
 * @author rakateja on 12/27/14.
 */
public interface UserRoleDaoInterface extends AbstractDaoInterface<UserRole> {

    public List<UserRole> findByUser(int idUser);

    public List<UserRole> findByRole(int idRole);
}
