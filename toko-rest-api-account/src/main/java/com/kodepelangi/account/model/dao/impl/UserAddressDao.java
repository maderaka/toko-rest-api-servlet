package com.kodepelangi.account.model.dao.impl;

import com.kodepelangi.account.entity.UserAddress;
import com.kodepelangi.account.model.dao.UserAddressDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rakateja on 12/7/14.
 */
public class UserAddressDao implements UserAddressDaoInterface {

    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<UserAddress> listUserAddress;

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
    private final String DELETE = "DELETE FROM `user_address` WHERE `id`=?";

    public UserAddressDao(Connection conn){
        this.conn = conn;
        this.listUserAddress = new ArrayList<>();
    }

    @Override
    public int create(UserAddress userAddress) {
        int lastIdInserted = 0;
        try{
            this.preparedStatement = this.conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, userAddress.getAddress());
            this.preparedStatement.setString(2, userAddress.getPostCode());
            this.preparedStatement.setString(3, userAddress.getCity());
            this.preparedStatement.setString(4, userAddress.getRegion());
            this.preparedStatement.setInt(5, userAddress.isMain()?1:0);
            this.preparedStatement.setInt(6, userAddress.getStatus());
            this.preparedStatement.setInt(7, userAddress.getCountry().getId());
            this.preparedStatement.setInt(8, userAddress.getUser().getId());

            if(this.preparedStatement.executeUpdate() == 1){
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if(this.resultSet.next()){
                    lastIdInserted = this.resultSet.getInt(1);
                }
            }


        }catch(SQLException ex){
            Logger.getLogger(UserAddressDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lastIdInserted;
    }

    @Override
    public boolean update(UserAddress userAddress) {
        try{
            this.preparedStatement = this.conn.prepareStatement(UPDATE);
            this.preparedStatement.setString(1, userAddress.getAddress());
            this.preparedStatement.setString(2, userAddress.getPostCode());
            this.preparedStatement.setString(3, userAddress.getCity());
            this.preparedStatement.setString(4, userAddress.getRegion());
            this.preparedStatement.setInt(5, userAddress.isMain()?1:0);
            this.preparedStatement.setInt(6, userAddress.getStatus());
            this.preparedStatement.setInt(7, userAddress.getCountry().getId());
            this.preparedStatement.setInt(8, userAddress.getUser().getId());

            this.preparedStatement.setInt(9, userAddress.getId());

            return this.preparedStatement.executeUpdate() ==1;

        }catch (SQLException e){
            Logger.getLogger(UserAddressDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public UserAddress findById(int id) {
        UserAddress userAddress = new UserAddress();
        try{
            this.preparedStatement = this.conn.prepareStatement(FIND_BY_ID);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                userAddress.setId(this.resultSet.getInt("id"));
                userAddress.setAddress(this.resultSet.getString("address"));
                userAddress.setCity(this.resultSet.getString("city"));
                userAddress.setMain(this.resultSet.getInt("is_main")==1);
                userAddress.setRegion(this.resultSet.getString("region"));
                userAddress.setPostCode(this.resultSet.getString("postcode"));
                userAddress.setCreatedAt(this.resultSet.getDate("created_at"));
                userAddress.getCountry().setId(this.resultSet.getInt("country_id"));
                userAddress.getUser().setId(this.resultSet.getInt("user_id"));
            }

        }catch (SQLException e){
            Logger.getLogger(UserAddressDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return userAddress;
    }

    @Override
    public List<UserAddress> findAll() {
        return null;
    }

    @Override
    public List<UserAddress> findByUser(int userId) {
        try{
            this.preparedStatement = this.conn.prepareStatement(FIND_BY_USER);
            this.preparedStatement.setInt(1, userId);
            this.resultSet = this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                UserAddress userAddress = new UserAddress();
                userAddress.setId(this.resultSet.getInt("id"));
                userAddress.setAddress(this.resultSet.getString("address"));
                userAddress.setCity(this.resultSet.getString("city"));
                userAddress.setMain(this.resultSet.getInt("is_main")==1);
                userAddress.setRegion(this.resultSet.getString("region"));
                userAddress.setPostCode(this.resultSet.getString("postcode"));
                userAddress.setCreatedAt(this.resultSet.getDate("created_at"));
                userAddress.getCountry().setId(this.resultSet.getInt("country_id"));
                userAddress.getUser().setId(this.resultSet.getInt("user_id"));
                this.listUserAddress.add(userAddress);
            }
        }catch (SQLException e){
            Logger.getLogger(UserAddressDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return this.listUserAddress;
    }

    @Override
    public boolean delete(int id) {
        try {
            this.preparedStatement = this.conn.prepareStatement(DELETE);
            this.preparedStatement.setInt(1, id);
            return this.preparedStatement.executeUpdate() ==1;
        } catch (SQLException e){
            Logger.getLogger(UserAddressDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean changeStatus(int id, int status) {
        try {
            this.preparedStatement = this.conn.prepareStatement(CHANGE_STATUS);
            this.preparedStatement.setInt(1,status);
            this.preparedStatement.setInt(2,status);

            return this.preparedStatement.executeUpdate() ==1;

        }catch (SQLException e){
            Logger.getLogger(UserAddressDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
