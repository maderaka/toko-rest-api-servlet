package com.kodepelangi.model.dao.impl;

import com.kodepelangi.entity.UserRole;
import com.kodepelangi.model.dao.UserRoleInterface;

import java.sql.*;
import java.util.*;

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
        int idLastCreated = 0;
        try {
            this.preparedStatement = this.conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, userRole.getUser().getId());
            this.preparedStatement.setInt(2, userRole.getRole().getId());

            if(this.preparedStatement.executeUpdate() ==1){
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if(this.resultSet.next()){
                    idLastCreated = this.resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idLastCreated;
    }

    @Override
    public boolean delete(int id) {
        try {
            this.preparedStatement = this.conn.prepareStatement(DELETE);
            this.preparedStatement.setInt(1,id);
            return this.preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(UserRole userRole) {
        try {
            this.preparedStatement = this.conn.prepareStatement(UPDATE);
            this.preparedStatement.setInt(1, userRole.getUser().getId());
            this.preparedStatement.setInt(2, userRole.getRole().getId());
            this.preparedStatement.setInt(1, userRole.getId());

            return this.preparedStatement.executeUpdate() ==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserRole findById(int id) {
        UserRole uR = new UserRole();
        try {
            this.preparedStatement = this.conn.prepareStatement(SELECT_BY_ID);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                uR.setId(this.resultSet.getInt("id"));
                uR.getUser().setId(this.resultSet.getInt("user_id"));
                uR.getRole().setId(this.resultSet.getInt("role_id"));
                uR.setCreatedAt(new java.util.Date(this.resultSet.getDate("created_at").getTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uR;
    }

    @Override
    public List<UserRole> findByUser(int idUser) {
        List<UserRole> list = new ArrayList<>();
        try{
            this.preparedStatement = this.conn.prepareStatement(SELECT_BY_USER);
            this.preparedStatement.setInt(1,idUser);
            this.resultSet = this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                UserRole userRole = new UserRole();
                userRole.setId(this.resultSet.getInt("id"));
                userRole.getUser().setId(this.resultSet.getInt("user_id"));
                userRole.getRole().setId(this.resultSet.getInt("role_id"));
                userRole.setCreatedAt(new java.util.Date(this.resultSet.getDate("created_at").getTime()));
                list.add(userRole);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<UserRole> findByRole(int idRole) {
        List<UserRole> list = new ArrayList<>();
        try{
            this.preparedStatement = this.conn.prepareStatement(SELECT_BY_ROLE);
            this.preparedStatement.setInt(1,idRole);
            this.resultSet = this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                UserRole userRole = new UserRole();
                userRole.setId(this.resultSet.getInt("id"));
                userRole.getUser().setId(this.resultSet.getInt("user_id"));
                userRole.getRole().setId(this.resultSet.getInt("role_id"));
                userRole.setCreatedAt(new java.util.Date(this.resultSet.getDate("created_at").getTime()));
                list.add(userRole);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    private final static String CREATE = "INSERT INTO `\" + TABLE_NAME + \"`(`user_id`,`role_id`) VALUES(?,?)";

    private final static String UPDATE = "UPDATE `\" + TABLE_NAME + \"` SET `user_id`=?, `role_id`=? WHERE `id`=?";

    private final static String DELETE = "DELETE FROM `\" + TABLE_NAME + \"` WHERE `id`=?";

    private final static String SELECT_BY_ID = "SELECT * FROM `\" + TABLE_NAME + \"` WHERE `id`=?";

    private final static String SELECT_BY_USER = "SELECT * FROM `\" + TABLE_NAME + \"` WHERE `user_id`=?";

    private final static String SELECT_BY_ROLE = "SELECT * FROM `\" + TABLE_NAME + \"` WHERE `role_id`=?";
}
