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

    public AbstractDao(Connection connection){
        this.conn = connection;
    }

    protected int add(String query, String[] params){
        try {
            this.preparedStatement = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            for (int i=1;i<params.length+1;i++){
                this.preparedStatement.setString(i, params[i - 1]);
            }

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

}
