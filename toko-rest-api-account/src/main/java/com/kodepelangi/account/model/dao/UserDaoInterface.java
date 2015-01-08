package com.kodepelangi.account.model.dao;

import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.account.entity.User;

/**
 * @author rakateja on 12/4/14.
 */
public interface UserDaoInterface extends AbstractDaoInterface<User> {

    public boolean changeStatus(boolean status);

}
