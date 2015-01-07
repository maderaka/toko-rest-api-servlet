package com.kodepelangi.model.dao;

import com.kodepelangi.entity.User;

/**
 * @author rakateja on 12/4/14.
 */
public interface UserDaoInterface extends AbstractDaoInterface<User> {

    public boolean changeStatus(boolean status);

}
