package com.kodepelangi.model.dao.impl;

import com.kodepelangi.entity.Role;
import com.kodepelangi.model.dao.AbstractDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Raka Teja <rakatejaa@gmail.com>
 */
public class RoleDao implements AbstractDaoInterface<Role> {

    private PreparedStatement preparedStatement;
    private Connection conn;
    private ResultSet resultSet;
    public RoleDao(Connection conn){
        this.conn = conn;
    }

    /**
     *
     * @param role Role
     * @return
     */
    @Override
    public int create(Role role) {
        int lastInsertedId = 0;
        try {
            this.preparedStatement = this.conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1,role.getTitle());

            if(this.preparedStatement.executeUpdate() == 1){
                this.resultSet = preparedStatement.getGeneratedKeys();
                if(this.resultSet.next()){
                    lastInsertedId = this.resultSet.getInt(1);
                }
            }

        }catch (SQLException ex){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }

        return lastInsertedId;
    }

    /**
     *
     * @param role
     * @return
     */
    @Override
    public boolean update(Role role) {
        try {
            this.preparedStatement = this.conn.prepareStatement(UPDATE);
            this.preparedStatement.setString(1, role.getTitle());
            this.preparedStatement.setInt(2, role.getId());
            return this.preparedStatement.executeUpdate() ==1;

        }catch (SQLException e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        try {
            this.preparedStatement = this.conn.prepareStatement(DELETE);
            this.preparedStatement.setInt(1, id);
            return this.preparedStatement.executeUpdate() ==1;
        }catch (SQLException e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    /**
     *
     * @param id int
     * @return
     */
    @Override
    public Role findById(int id) {
        Role r = new Role();
        try{
            this.preparedStatement = this.conn.prepareStatement(SELECT_BY_ID);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if(this.resultSet.next()){
                r.setId(this.resultSet.getInt("id"));
                r.setTitle(this.resultSet.getString("title"));
            }

        }catch(SQLException e){

        }
        return r;
    }

    /**
     *
     * @return List<Role>
     */
    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        try {
            this.preparedStatement = this.conn.prepareStatement(SELECT_ALL);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()){
                Role r = new Role();
                r.setId(this.resultSet.getInt("id"));
                r.setTitle(this.resultSet.getString("title"));

                list.add(r);
            }
        }catch(SQLException e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return list;
    }

    public List<Role> findByUser(){
        List<Role> list = new ArrayList<>();
        return list;
    }

    private static final String CREATE = "INSERT INTO `roles`(`title`) VALUES(?)";
    private static final String UPDATE = "UPDATE `roles` SET `title`=? WHERE `id`=?";
    private static final String SELECT_BY_ID = "SELECT * FROM `roles` WHERE `id`=?";
    private static final String DELETE = "DELETE FROM `roles` WHERE `id`=?";
    private static final String SELECT_ALL = "SELECT * FROM `roles`";
}
