package com.kodepelangi.product.model.dao.impl;

import com.kodepelangi.app.model.AbstractDao;
import com.kodepelangi.product.entity.Product;
import com.kodepelangi.product.model.dao.ProductDaoInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raka Teja<rakatejaa@gmail.com>
 */
public class ProductDao extends AbstractDao implements ProductDaoInterface {

    public ProductDao(Connection connection) {
        super(connection);
    }

    @Override
    public int create(Product obj) {
        int lastIdInserted = 0;
        try {
            this.query = "INSERT INTO `product`(`code`,`title`,`price`,`desc`,`status`,`brand_id`,`category_id`) VALUES(?,?,?,?,?,?,?)";
            this.preparedStatement = this.conn.prepareStatement(this.query, Statement.RETURN_GENERATED_KEYS);
            lastIdInserted = this.add(this.preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastIdInserted;
    }

    @Override
    public boolean update(Product obj) {
        try {
            this.query = "UPDATE `brand` SET `code`=?, `title`=?,`price`=?,`desc`=?,`status`=?,`brand_id`=?,`category`=? WHERE `id`=?";
            this.preparedStatement = this.conn.prepareStatement(this.query);
            return this.preparedStatement.executeUpdate() ==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        this.query = "DELETE FROM `product` WHERE `id`=?";
        return super.delete(id);
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        try {
            this.query = "SELECT * FROM `product` WHERE `id`=?";
            this.resultSet = super.selectById(id);
            if(this.resultSet.next()){
                product.setId(this.resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            this.query = "SELECT * FROM `product`";
            this.resultSet = this.selectMany();
            while (this.resultSet.next()){
                Product p = new Product();
                p.setId(this.resultSet.getInt("id"));
                p.setCode(this.resultSet.getString("code"));
                p.setTitle(this.resultSet.getString("title"));
                p.setPrice(this.resultSet.getBigDecimal("price"));
                p.setDesc(this.resultSet.getString("desc"));
                p.setStatus(this.resultSet.getInt("status"));
                p.getBrand().setId(this.resultSet.getInt("brand_id"));
                p.getCategory().setId(this.resultSet.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}