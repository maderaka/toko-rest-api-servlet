package com.kodepelangi.service.controller;

import com.kodepelangi.product.entity.Category;
import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.product.model.DaoFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Raka Teja<rakatejaa@gmail.com>
 */

@WebServlet(
        name="CategoryServletController",
        description = "Category API Endpoint",
        urlPatterns = {"/category"}
)

public class CategoryController extends AbstractController {

    AbstractDaoInterface<Category> categoryDao;
    DaoFactory daoFactory;
    public CategoryController(){
        super();
        this.daoFactory = new DaoFactory();
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);

        String id = this.getRequest().getParameter("id");
        try {
            this.daoFactory.open();
            this.categoryDao = this.daoFactory.getCategoryDao();
            if(id != null){
                Category category = this.categoryDao.findById(Integer.parseInt(id));
                this.responseJson(category);
            }else{
                List<Category> list = this.categoryDao.findAll();
                this.responseJson(list);
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
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request,response);
        System.out.println(this.getInputBodyContent());
        Category category = this.getGson().fromJson(this.getInputBodyContent(), Category.class);
        System.out.println("Category Product");
        System.out.println("Aku mau buktikan");

        try {
            this.daoFactory.open();
            this.categoryDao = this.daoFactory.getCategoryDao();
            int id = this.categoryDao.create(category);

            category = this.categoryDao.findById(id);
            this.daoFactory.close();

            this.responseJson(category);

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
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
        int id = Integer.parseInt(this.getRequest().getParameter("id"));
        Category category = this.getGson().fromJson(this.getInputBodyContent(), Category.class);
        category.setId(id);

        try {
            this.daoFactory.open();
            this.categoryDao = this.daoFactory.getCategoryDao();
            this.categoryDao.update(category);

            this.responseJson(this.categoryDao.findById(id));

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
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        this.setRequestResponse(request, response);
        int id = Integer.parseInt(this.getRequest().getParameter("id"));
        try {
            this.daoFactory.open();

            this.categoryDao = this.daoFactory.getCategoryDao();
            this.categoryDao.delete(id);
            this.responseJson(null);

            this.daoFactory.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
