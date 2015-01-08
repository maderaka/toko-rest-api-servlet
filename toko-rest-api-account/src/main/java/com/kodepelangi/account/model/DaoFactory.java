package com.kodepelangi.account.model;

import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.account.model.dao.UserDaoInterface;
import com.kodepelangi.account.model.dao.impl.UserDao;
import com.kodepelangi.account.model.dao.impl.RoleDao;
import com.kodepelangi.account.model.dao.UserRoleDaoInterface;
import com.kodepelangi.account.model.dao.impl.UserRoleDao;

/**
 * @author rakateja on 1/8/15.
 */
public class DaoFactory extends com.kodepelangi.app.model.DaoFactory {

    /**
     *
     * @return UserDaoInterface
     */
    public UserDaoInterface getUserDao(){
        UserDaoInterface userDao = new UserDao(this.conn);
        return userDao;
    }

    /**
     *
     * @return UserDaoInterface
     */
    public AbstractDaoInterface getRoleDao(){
        AbstractDaoInterface roleDao = new RoleDao(this.conn);
        return roleDao;
    }

    /**
     *
     * @return UserRoleInterface
     */
    public UserRoleDaoInterface getUserRoleDao(){
        UserRoleDaoInterface userRoleDao = new UserRoleDao(this.conn);
        return userRoleDao;
    }
}
