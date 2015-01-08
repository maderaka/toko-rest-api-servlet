package com.kodepelangi.app;

import com.kodepelangi.model.dao.AbstractDaoInterface;
import com.kodepelangi.model.dao.UserDaoInterface;
import com.kodepelangi.model.dao.UserRoleDaoInterface;
import com.kodepelangi.model.dao.impl.CategoryDao;
import com.kodepelangi.model.dao.impl.RoleDao;
import com.kodepelangi.model.dao.impl.UserDao;
import com.kodepelangi.model.dao.impl.UserRoleDao;

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
    public AbstractDaoInterface getRoleDao(){
        AbstractDaoInterface roleDao = new RoleDao(this.conn);
        return roleDao;
    }

    /**
     *
     * @return UserRoleInterface
     */
    public UserRoleDaoInterface getUserRoleDao(){
        UserRoleDaoInterface userRoleDao = new UserRoleDao(this.conn);
        return userRoleDao;
    }

    public AbstractDaoInterface getCategoryDao(){
        AbstractDaoInterface categoryDao = new CategoryDao(this.conn);
        return categoryDao;
    }
}
