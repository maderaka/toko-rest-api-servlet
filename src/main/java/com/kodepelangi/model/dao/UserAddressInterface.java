package com.kodepelangi.model.dao;

import com.kodepelangi.entity.UserAddress;

import java.util.List;

/**
 * @author rakateja on 12/7/14.
 */
public interface UserAddressInterface {

    public int create(UserAddress userAddress);

    public boolean update(UserAddress userAddress);

    public UserAddress findById(int id);

    public List<UserAddress> findByUser(int userId);

    public boolean delete(int id);

    public boolean changeStatus(int id, int status);
}
