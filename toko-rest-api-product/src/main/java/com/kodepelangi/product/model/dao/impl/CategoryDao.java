package com.kodepelangi.product.model.dao.impl;

import com.kodepelangi.product.entity.Category;
import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.app.model.AbstractDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raka Teja<rakatejaa@gmail.com>
 */
public class CategoryDao extends AbstractDao implements AbstractDaoInterface<Category>{

    public CategoryDao(Connection connection) {
        super(connection);
    }

    @Override
    public int create(Category category) {
        int lastInsertedId = 0;
        try {
            this.preparedStatement = this.conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, category.getTitle());
            this.preparedStatement.setString(2, category.getDesc());

            if(this.preparedStatement.executeUpdate() ==1){
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if(this.resultSet.next()){
                    lastInsertedId = this.resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastInsertedId;
    }

    @Override
    public boolean update(Category category) {
        try{
            this.preparedStatement = this.conn.prepareStatement(UPDATE);
            this.preparedStatement.setString(1, category.getTitle());
            this.preparedStatement.setString(2, category.getDesc());
            this.preparedStatement.setInt(3, category.getId());

            return this.preparedStatement.executeUpdate() ==1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            this.preparedStatement = this.conn.prepareStatement(DELETE);
            this.preparedStatement.setInt(1,id);
            return this.preparedStatement.executeUpdate()==1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findById(int id) {
        Category category = new Category();
        try {
            this.preparedStatement = this.conn.prepareStatement(FIND_BY_ID);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                category.setId(this.resultSet.getInt("id"));
                category.setTitle(this.resultSet.getString("title"));
                category.setDesc(this.resultSet.getString("desc"));
                category.setCreatedDate(this.resultSet.getDate("created_at"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try {
            this.preparedStatement = this.conn.prepareStatement(FIND_ALL);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()){
                Category c = new Category();
                c.setId(this.resultSet.getInt("id"));
                c.setTitle(this.resultSet.getString("title"));
                c.setDesc(this.resultSet.getString("desc"));
                c.setCreatedDate(this.resultSet.getDate("created_at"));

                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static final String CREATE = "INSERT INTO `product_category`(`title`,`desc`) VALUES(?,?)";

    private static final String UPDATE = "UPDATE `product_category` SET `title`=?, `desc`=? WHERE `id`=?";

    private static final String DELETE = "DELETE FROM `product_category` WHERE `id`=?";

    private static final String FIND_BY_ID = "SELECT * FROM `product_category` WHERE `id`=?";

    private static final String FIND_ALL = "SELECT * FROM `product_category`";
}
