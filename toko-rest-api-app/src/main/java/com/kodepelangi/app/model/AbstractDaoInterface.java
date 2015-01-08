package com.kodepelangi.app.model;

import java.util.List;

/**
 * @author Raka Teja <rakatejaa@gmail.com>
 */
public interface AbstractDaoInterface<T>{

    /**
     *
     * @param obj T
     * @return int
     */
    public int create(T obj);

    /**
     *
     * @param obj T
     * @return bool
     */
    public boolean update(T obj);

    /**
     *
     * @param id int
     * @return bool
     */
    public boolean delete(int id);

    /**
     *
     * @param id int
     * @return T
     */
    public T findById(int id);

    /**
     *
     * @return List
     */
    public List<T> findAll();

}
