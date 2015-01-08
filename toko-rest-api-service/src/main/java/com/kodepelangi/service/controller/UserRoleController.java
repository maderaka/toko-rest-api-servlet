package com.kodepelangi.service.controller;

import com.google.gson.JsonObject;
import com.kodepelangi.account.model.DaoFactory;
import com.kodepelangi.account.entity.UserRole;
import com.kodepelangi.account.model.dao.UserRoleDaoInterface;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author I Made Raka Teja <rakatejaa@gmail.com>
 */

@WebServlet(
        name = "UserRoleController",
        description = "Role endpoint",
        urlPatterns = {"/user/role"}
)
public class UserRoleController extends AbstractController {
    private DaoFactory daoFactory;
    private UserRoleDaoInterface userRoleDao;
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

            if(id== null){
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
        String user = this.getRequest().getParameter("user");

        String input = this.getInputBodyContent();
        JsonObject jsonObject = (JsonObject) this.getGson().fromJson(input,Object.class);

        UserRole userRole = new UserRole();
        userRole.getUser().setId(Integer.parseInt(user));
        userRole.getRole().setId(jsonObject.get("role_id").getAsInt());

        System.out.println("role id = "+userRole.getRole().getId());

        try {
            this.daoFactory.open();
            this.userRoleDao = this.daoFactory.getUserRoleDao();
            int id = this.userRoleDao.create(userRole);

            this.responseJson(this.userRoleDao.findById(id));
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
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
    }
}
