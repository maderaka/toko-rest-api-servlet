package com.kodepelangi.app;

import com.kodepelangi.model.dao.RoleInterface;
import com.kodepelangi.model.dao.UserDaoInterface;
import com.kodepelangi.model.dao.impl.RoleDao;
import com.kodepelangi.model.dao.impl.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rakateja on 12/4/14.
 */
public class DaoFactory {
    private Connection conn;
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String CLASS_DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost/dbcommerce";


    public void open() throws InstantiationException, IllegalAccessException, SQLException
    {
        if(this.conn == null || this.conn.isClosed()) {
            try {
                Class.forName(CLASS_DRIVER_NAME).newInstance();
                this.conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void close() throws SQLException
    {
        if(this.conn != null || !this.conn.isClosed()){
            this.conn.close();
        }
    }

    /**
     *
     * @return UserDaoInterface
     */
    public UserDaoInterface getUserDao(){
        UserDaoInterface userDao = new UserDao(this.conn);
        return userDao;
    }

    /**
     *
     * @return UserDaoInterface
     */
    public RoleInterface getRoleDao(){
        RoleInterface roleDao = new RoleDao(this.conn);
        return roleDao;
    }
}
