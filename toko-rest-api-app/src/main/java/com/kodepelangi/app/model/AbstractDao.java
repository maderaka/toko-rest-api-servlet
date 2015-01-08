package com.kodepelangi.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Raka Teja<rakatejaa@gmail.com>
 */
public class AbstractDao {

    protected Connection conn;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet;

    public AbstractDao(Connection connection){
        this.conn = connection;
    }

}
