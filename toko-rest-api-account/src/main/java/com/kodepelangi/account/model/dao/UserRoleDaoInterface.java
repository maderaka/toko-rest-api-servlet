package com.kodepelangi.account.model.dao;

import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.account.entity.UserRole;

import java.util.List;

/**
 * @author rakateja on 12/27/14.
 */
public interface UserRoleDaoInterface extends AbstractDaoInterface<UserRole> {

    public List<UserRole> findByUser(int idUser);

    public List<UserRole> findByRole(int idRole);
}
