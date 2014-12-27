package com.kodepelangi.model.dao.impl;

import com.kodepelangi.entity.UserAddress;
import com.kodepelangi.model.dao.UserAddressInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author rakateja on 12/7/14.
 */
public class UserAddressDao implements UserAddressInterface {

    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private final String CREATE = "INSERT INTO user_address(" +
            "`address`,`postcode`," +
            "`city`,`region`,`is_main`," +
            "`status`,`country_id`,`user_id`" +
            "VALUES(?,?,?,?,?,?,?,?))";

    private final String UPDATE = "UPDATE `user_address` SET " +
            "`address`=?,`postcode`=?," +
            "`city`=?,`region`=?," +
            "`is_main`=?," +
            "`status`=?,`country_id`=?," +
            "`user_id`=? WHERE `id`=?";

    private final String FIND_BY_ID ="SELECT * FROM `user_address` WHERE `id`=?";

    private final String FIND_BY_USER = "SELECT * FROM `user_address` WHERE `user_id`=?";

    private final String CHANGE_STATUS = "UPDATE `user_address` SET `status`=? WHERE `id`=?";

    public UserAddressDao(Connection conn){
        this.conn = conn;
    }

    @Override
    public int create(UserAddress userAddress) {
        try{
            this.preparedStatement = this.conn.prepareStatement(CREATE);

        }catch(SQLException ex){

        }

        return 0;
    }

    @Override
    public boolean update(UserAddress userAddress) {
        return false;
    }

    @Override
    public UserAddress findById(int id) {
        return null;
    }

    @Override
    public List<UserAddress> findByUser(int userId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean changeStatus(int id, int status) {
        return false;
    }
}
