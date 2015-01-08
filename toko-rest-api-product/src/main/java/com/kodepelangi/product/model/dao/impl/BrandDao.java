package com.kodepelangi.product.model.dao.impl;

import com.kodepelangi.product.entity.Brand;
import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.app.model.AbstractDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rakateja on 1/8/15.
 */
public class BrandDao extends AbstractDao implements AbstractDaoInterface<Brand>{

    public BrandDao(Connection connection){
        super(connection);
    }

    @Override
    public int create(Brand brand) {
        try {
            this.preparedStatement = this.conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, brand.getTitle());
            this.preparedStatement.setString(2, brand.getSlug());
            this.preparedStatement.setString(3, brand.getDesc());
            this.preparedStatement.setString(4, brand.getImageUrl());
            if(this.preparedStatement.executeUpdate() == 1){
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if(this.resultSet.next()){
                    return this.resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean update(Brand b) {
        try {
            this.preparedStatement = this.conn.prepareStatement(UPDATE);
            this.preparedStatement.setString(1, b.getTitle());
            this.preparedStatement.setString(2, b.getSlug());
            this.preparedStatement.setString(3, b.getDesc());
            this.preparedStatement.setString(4, b.getImageUrl());
            this.preparedStatement.setInt(5, b.getId());

            return this.preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            this.preparedStatement = this.conn.prepareStatement(DELETE);
            this.preparedStatement.setInt(1,id);
            return this.preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Brand findById(int id) {
        Brand b = new Brand();
        try {
            this.preparedStatement = this.conn.prepareStatement(FIND_BY_ID);
            this.preparedStatement.setInt(1, id);
            this.resultSet  = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                b.setId(this.resultSet.getInt("id"));
                b.setTitle(this.resultSet.getString("title"));
                b.setSlug(this.resultSet.getString("slug"));
                b.setDesc(this.resultSet.getString("desc"));
                b.setImageUrl(this.resultSet.getString("image_url"));
                b.setCreatedAt(this.resultSet.getDate("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return b;
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> list = new ArrayList<>();
        try {
            this.preparedStatement = this.conn.prepareStatement(FIND_ALL);
            this.resultSet = this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                Brand b = new Brand();
                b.setId(this.resultSet.getInt("id"));
                b.setTitle(this.resultSet.getString("title"));
                b.setSlug(this.resultSet.getString("slug"));
                b.setDesc(this.resultSet.getString("desc"));
                b.setImageUrl(this.resultSet.getString("image_url"));
                b.setCreatedAt(this.resultSet.getDate("created_at"));

                list.add(b);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static final String CREATE = "INSERT INTO `brand`(`title`,`slug`,`desc`,`image_url`) VALUES(?,?,?,?)";

    private static final String UPDATE = "UPDATE `brand` SET `title`=?, `slug`=?,`desc`=?,`image_url`=? WHERE `id`=?";

    private static final String DELETE = "DELETE FROM `brand` WHERE `id`=?";

    private static final String FIND_BY_ID = "SELECT * FROM `brand` WHERE `id`=?";

    private static final String FIND_ALL = "SELECT * FROM `brand`";
}
