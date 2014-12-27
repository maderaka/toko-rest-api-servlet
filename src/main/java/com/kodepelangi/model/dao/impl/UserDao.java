package com.kodepelangi.model.dao.impl;

import com.kodepelangi.entity.User;
import com.kodepelangi.model.dao.UserDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rakateja on 12/4/14.
 */
public class UserDao implements UserDaoInterface {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int add(User user) {
        int lastInsertedId = 0;
        try {
            this.preparedStatement = this.conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, user.getUsername());
            this.preparedStatement.setString(2, user.getEmail());
            this.preparedStatement.setString(3, user.getPhone());
            this.preparedStatement.setString(4, user.getDisplayName());
            this.preparedStatement.setString(5, user.getFirstName());
            this.preparedStatement.setString(6, user.getLastName());
            this.preparedStatement.setString(7, user.getAvatar());
            if(user.getDob() != null){
                this.preparedStatement.setDate(8, new Date(user.getDob().getTime()));
            }else{
                this.preparedStatement.setDate(8, null);
            }
            this.preparedStatement.setInt(9, user.getGender());
            this.preparedStatement.setString(10, user.getRememberToken());
            this.preparedStatement.setInt(11, user.getStatus());
            if(this.preparedStatement.executeUpdate() == 1){
                this.resultSet = preparedStatement.getGeneratedKeys();
                if(this.resultSet.next()){
                    lastInsertedId = this.resultSet.getInt(1);
                }
            }

        }catch (SQLException ex){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try{
                if(this.resultSet != null){
                    this.resultSet.close();
                }
                if(this.preparedStatement != null){
                    this.preparedStatement.close();
                }
            } catch (SQLException ex){
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return lastInsertedId;
    }

    @Override
    public boolean update(User user) {
        try {
            this.preparedStatement = this.conn.prepareStatement(UPDATE);
            this.preparedStatement.setString(1, user.getUsername());
            this.preparedStatement.setString(2, user.getEmail());
            this.preparedStatement.setString(3, user.getPhone());
            this.preparedStatement.setString(4, user.getDisplayName());
            this.preparedStatement.setString(5, user.getFirstName());
            this.preparedStatement.setString(6, user.getLastName());
            this.preparedStatement.setString(7, user.getAvatar());
            if(user.getDob() != null) this.preparedStatement.setDate(8,new Date(user.getDob().getTime()));
            else this.preparedStatement.setDate(8,null);
            this.preparedStatement.setInt(9, user.getGender());
            this.preparedStatement.setString(10, user.getRememberToken());
            this.preparedStatement.setInt(11, user.getStatus());
            this.preparedStatement.setInt(12, user.getId());

            return this.preparedStatement.executeUpdate() ==1;
        } catch (SQLException ex){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            this.preparedStatement = this.conn.prepareStatement(DELETE);
            this.preparedStatement.setInt(1, id);

            return this.preparedStatement.executeUpdate() ==1;

        } catch (SQLException ex){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                this.resultSet.close();
                this.preparedStatement.close();
            } catch (SQLException ex){
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        try {
            this.preparedStatement = this.conn.prepareStatement(SELECT_BY_ID);
            this.preparedStatement.setInt(1, id);

            this.resultSet = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                user.setId(this.resultSet.getInt("id"));
                user.setUsername(this.resultSet.getString("username"));
                user.setEmail(this.resultSet.getString("email"));
                user.setPhone(this.resultSet.getString("phone"));
                user.setDisplayName(this.resultSet.getString("display_name"));
                user.setFirstName(this.resultSet.getString("first_name"));
                user.setLastName(this.resultSet.getString("last_name"));
                user.setAvatar(this.resultSet.getString("avatar"));
                user.setDob(this.resultSet.getDate("dob"));
                user.setGender(this.resultSet.getInt("gender"));
                user.setRememberToken(this.resultSet.getString("remember_token"));
                user.setStatus(this.resultSet.getInt("status"));
                user.setCreatedAt(this.resultSet.getDate("created_at"));
            }

            System.out.println("username = "+user.getUsername());
        } catch (SQLException ex){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.preparedStatement.close();
            } catch (SQLException ex){
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        try {
            this.preparedStatement = this.conn.prepareStatement(SELECT_ALL);
            this.preparedStatement.setInt(1,0);
            this.preparedStatement.setInt(2,10);

            this.resultSet = this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                User user = new User();
                user.setId(this.resultSet.getInt("id"));
                user.setUsername(this.resultSet.getString("username"));
                user.setEmail(this.resultSet.getString("email"));
                user.setPhone(this.resultSet.getString("phone"));
                user.setDisplayName(this.resultSet.getString("display_name"));
                user.setFirstName(this.resultSet.getString("first_name"));
                user.setLastName(this.resultSet.getString("last_name"));
                user.setAvatar(this.resultSet.getString("avatar"));
                user.setDob(this.resultSet.getDate("dob"));
                user.setGender(this.resultSet.getInt("gender"));
                user.setRememberToken(this.resultSet.getString("remember_token"));
                user.setStatus(this.resultSet.getInt("status"));
                user.setCreatedAt(this.resultSet.getDate("created_at"));

                System.out.println(user.getUsername());
                list.add(user);
            }

        } catch (SQLException ex){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return list;
    }

    @Override
    public boolean changeStatus(boolean status) {
        return false;
    }

    private final String CREATE = "INSERT INTO `user`(" +
            "`username`,`email`,`phone`," +
            "`display_name`,`first_name`,`last_name`," +
            "`avatar`,`dob`,`gender`,`remember_token`," +
            "`status`) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE `user` SET `username`=?,`email`=?," +
            "`phone`=?,`display_name`=?,`first_name`=?,`last_name`=?,`avatar`=?,`dob`=?," +
            "`gender`=?,`remember_token`=?,`status`=? WHERE `id`=?";
    private final String DELETE = "DELETE `user` WHERE `id`=?";
    private final String SELECT_BY_ID = "SELECT * FROM `user` WHERE `id`=?";
    private final String SELECT_ALL = "SELECT * FROM `user` limit ?,?";
}
