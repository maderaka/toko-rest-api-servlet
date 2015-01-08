package com.kodepelangi.service.controller;

import com.kodepelangi.account.model.DaoFactory;
import com.kodepelangi.account.entity.Role;
import com.kodepelangi.app.model.AbstractDaoInterface;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Raka Teja<rakatejaa@gmail.com>
 */

@WebServlet(
        name = "RoleServletController",
        description = "Role endpoint",
        urlPatterns = {"/role"}
)
public class RoleController extends AbstractController {

    DaoFactory daoFactory;
    AbstractDaoInterface<Role> roleInterface;
    public RoleController(){
        super();
        this.daoFactory = new DaoFactory();
    }

    /**
     * Role get endpoint
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        this.setRequestResponse(request, response);

        String id = request.getParameter("id");
        try{
            this.daoFactory.open();
            this.roleInterface = this.daoFactory.getRoleDao();
            if(id == null){
                List<Role> list = this.roleInterface.findAll();
                this.responseJson(list);
            }else{
                Role role = this.roleInterface.findById((Integer.parseInt(id)));
                this.responseJson(role);
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

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.setRequestResponse(request, response);
        Role role = this.getGson().fromJson(this.getInputBodyContent(),Role.class);
        try{
            this.daoFactory.open();
            this.roleInterface = this.daoFactory.getRoleDao();
            int id = this.roleInterface.create(role);

            Role r = this.roleInterface.findById(id);
            this.responseJson(r);

        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response){
        this.setRequestResponse(request, response);
        String id = this.getRequest().getParameter("id");

        Role role = this.getGson().fromJson(this.getInputBodyContent(), Role.class);
        try {
            this.daoFactory.open();
            this.roleInterface = this.daoFactory.getRoleDao();

            role.setId(Integer.parseInt(id));
            this.roleInterface.update(role);
            this.responseJson(this.roleInterface.findById(Integer.parseInt(id)));

            this.daoFactory.close();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response){
        this.setRequestResponse(request, response);
        String id = this.getRequest().getParameter("id");
        try {
            this.daoFactory.open();
            this.roleInterface = this.daoFactory.getRoleDao();
            this.roleInterface.delete(Integer.parseInt(id));

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
