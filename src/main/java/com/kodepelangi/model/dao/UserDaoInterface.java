package com.kodepelangi.model.dao;

import com.kodepelangi.entity.User;

import java.util.List;

/**
 * @author rakateja on 12/4/14.
 */
public interface UserDaoInterface {

    public int add(User user);

    public boolean update(User user);

    public boolean delete(int id);

    public User findById(int id);

    public List<User> findAll();

    public boolean changeStatus(boolean status);

}
