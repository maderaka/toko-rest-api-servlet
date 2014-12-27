package com.kodepelangi.model.dao.impl;

import com.kodepelangi.entity.UserRole;
import com.kodepelangi.model.dao.UserRoleInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author rakateja on 12/27/14.
 */
public class UserRoleDao implements UserRoleInterface {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserRoleDao(Connection conn){
        this.conn = conn;
    }

    @Override
    public int create(UserRole userRole) {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(UserRole userRole) {
        return false;
    }

    @Override
    public List<UserRole> findAllByUser(int idUser) {
        return null;
    }
}
