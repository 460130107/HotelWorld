package edu.nju.hotel.dao;

/**
 * Created by zhouxiaofan on 2017/1/22.
 */
public interface BaseDao {
    void insert(Object obj);

    void delete(Class<?> c,long id);

    void update(Object obj);

    Object load(Class<?> c,long id);
}
