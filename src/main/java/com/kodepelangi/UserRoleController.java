package com.kodepelangi;

import com.kodepelangi.app.DaoFactory;
import com.kodepelangi.entity.User;
import com.kodepelangi.entity.UserRole;
import com.kodepelangi.model.dao.UserRoleInterface;
import com.kodepelangi.model.dao.impl.UserRoleDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author I Made Raka Teja <rakatejaa@gmail.com>
 */

@WebServlet(
        name = "RoleServletController",
        description = "Role endpoint",
        urlPatterns = {"/user/role"}
)
public class UserRoleController extends AbstractController {
    private DaoFactory daoFactory;
    private UserRoleInterface userRoleDao;
    public UserRoleController(){
        this.daoFactory = new DaoFactory();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
        String user = this.getRequest().getParameter("user");
        String id = this.getRequest().getParameter("id");
        try {
            this.daoFactory.open();
            this.userRoleDao = this.daoFactory.getUserRoleDao();

            if(id!= null){
                List<UserRole> list =this.userRoleDao.findByUser(Integer.parseInt(user));
                this.responseJson(list);
            }else{
                UserRole userRole = this.userRoleDao.findById(Integer.parseInt(id));
                this.responseJson(userRole);
            }

            this.daoFactory.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
    }
}
