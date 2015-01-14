package com.kodepelangi.app.model;

import java.sql.*;
import java.util.List;

/**
 * @author Raka Teja<rakatejaa@gmail.com>
 */
public abstract class AbstractDao {

    protected Connection conn;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet;

    protected String query;

    public AbstractDao(Connection connection){
        this.conn = connection;
    }

    protected int add(){
        try {
            if(this.preparedStatement.executeUpdate()==1){
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if(this.resultSet.next()){
                    return this.resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected boolean delete(int id){
        try {
            this.preparedStatement = this.conn.prepareStatement(this.query);
            this.preparedStatement.setInt(1, id);
            return this.preparedStatement.executeUpdate() ==1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    protected ResultSet selectById(int id){
        try {
            this.preparedStatement = this.conn.prepareStatement(this.query);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.resultSet;
    }

    protected ResultSet selectMany(){
        try {
            this.preparedStatement = this.conn.prepareStatement(this.query);
            this.resultSet = this.preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.resultSet;
    }

}
