package com.kodepelangi;

import com.google.gson.Gson;
import com.kodepelangi.app.DaoFactory;
import com.kodepelangi.entity.User;
import com.kodepelangi.model.dao.UserDaoInterface;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

/**
 * @author rakateja on 12/6/14.
 */
@WebServlet("/user")
public class UserController extends AbstractController{

    private Gson gson;
    private String exMessage;
    private DaoFactory daoFactory;

    public UserController(){
        this.gson = new Gson();
        this.daoFactory = new DaoFactory();
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        this.setRequestResponse(request, response);

        String idUser = request.getParameter("id");
        try {
            this.daoFactory.open();
            UserDaoInterface userDao = this.daoFactory.getUserDao();

            if(idUser != null){
                this.responseJson(userDao.findById(Integer.parseInt(idUser)));
            }else {
                this.responseJson(userDao.findAll());
            }

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
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.setRequestResponse(request, response);

        User user = this.gson.fromJson(this.getInputBodyContent(), User.class);

        try {
            this.daoFactory.open();
            UserDaoInterface userDao = this.daoFactory.getUserDao();
            int id = userDao.add(user);
            this.responseJson(userDao.findById(id));

        } catch (InstantiationException e) {
            exMessage += e.getMessage();
        } catch (IllegalAccessException e) {
            exMessage += e.getMessage();
        } catch (SQLException e) {
            exMessage += e.getMessage();
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
        if(id != null){
            try {
                User user = this.gson.fromJson(this.getInputBodyContent(), User.class);
                user.setId(Integer.parseInt(id));

                this.daoFactory.open();
                UserDaoInterface userDao = this.daoFactory.getUserDao();
                userDao.update(user);
                this.responseJson(userDao.findById(user.getId()));

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
        String id = this.getRequest().getParameter("id");
        if(id != null){
            try {
                this.daoFactory.open();

                UserDaoInterface userDao = this.daoFactory.getUserDao();
                userDao.delete(Integer.parseInt(id));
                this.responseJson(null);

                this.daoFactory.close();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{

        }
    }
}
