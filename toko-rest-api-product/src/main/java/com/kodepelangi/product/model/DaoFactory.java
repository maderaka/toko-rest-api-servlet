package com.kodepelangi.product.model;

import com.kodepelangi.app.model.AbstractDaoInterface;
import com.kodepelangi.product.model.dao.impl.CategoryDao;

/**
 * @author rakateja on 1/8/15.
 */
public class DaoFactory extends com.kodepelangi.app.model.DaoFactory {
    public AbstractDaoInterface getCategoryDao(){
        AbstractDaoInterface categoryDao = new CategoryDao(this.conn);
        return categoryDao;
    }
}
