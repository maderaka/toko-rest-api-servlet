package com.kodepelangi.account.model.dao;

import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.account.entity.UserAddress;

import java.util.List;

/**
 * @author rakateja on 12/7/14.
 */
public interface UserAddressDaoInterface extends AbstractDaoInterface<UserAddress> {

    public List<UserAddress> findByUser(int userId);

    public boolean changeStatus(int id, int status);
}
