package edu.nju.hotel.dao;

import edu.nju.hotel.model.UserEntity;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/22.
 */

public interface UserDao {
    void save(UserEntity userEntity);

    List<UserEntity> findAll();

    boolean verify(String username,String password);

    UserEntity getUserByName(String username);

    UserEntity getUserById(int id);

    void deleteInBatch();

    void deleteById(int id);

    void update(UserEntity userEntity);
}
