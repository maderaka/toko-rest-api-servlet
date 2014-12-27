package com.kodepelangi;

import com.google.gson.Gson;
import com.kodepelangi.app.DaoFactory;
import com.kodepelangi.entity.User;
import com.kodepelangi.model.dao.UserDaoInterface;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author rakateja on 12/6/14.
 */
@WebServlet("/user")
public class UserController extends HttpServlet{

    private Gson gson;
    private String exMessage;
    private DaoFactory daoFactory;

    public UserController(){
        this.gson = new Gson();
        this.daoFactory = new DaoFactory();
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String idUser = request.getParameter("id");
        String json;
        try {
            this.daoFactory.open();
            UserDaoInterface userDao = this.daoFactory.getUserDao();

            if(idUser != null){
                json = this.gson.toJson(userDao.findById(Integer.parseInt(idUser)));
            }else {
                json = this.gson.toJson(userDao.findAll());
            }

            try {
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            } catch (IOException e) {

            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{

            BufferedReader reader = request.getReader();
            StringBuilder builder = new StringBuilder();
            String aux;

            while((aux = reader.readLine()) != null){
                builder.append(aux);
            }

            User user = this.gson.fromJson(builder.toString(), User.class);

            try {
                this.daoFactory.open();
                UserDaoInterface userDao = this.daoFactory.getUserDao();
                int id = userDao.add(user);

                System.out.println("id = "+id);
                String json = gson.toJson(userDao.findById(id));

                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();

            } catch (InstantiationException e) {
                exMessage += e.getMessage();
            } catch (IllegalAccessException e) {
                exMessage += e.getMessage();
            } catch (SQLException e) {
                exMessage += e.getMessage();
            } catch(Exception e){
                exMessage += e.getMessage();
            }

        }catch(IOException ex){

        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        if(id != null){
            try {
                this.daoFactory.open();
                UserDaoInterface userDao = this.daoFactory.getUserDao();

                BufferedReader reader = request.getReader();
                StringBuilder builder = new StringBuilder();
                String aux;

                while((aux = reader.readLine()) != null){
                    builder.append(aux);
                }
                User user = this.gson.fromJson(builder.toString(), User.class);

                user.setId(Integer.parseInt(id));
                userDao.update(user);

                String json = this.gson.toJson(userDao.findById(user.getId()));
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response){

    }
}
