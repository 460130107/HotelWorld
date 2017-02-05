package edu.nju.hotel.data.dao;

import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.util.VerifyResult;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/22.
 */

public interface UserDao {
    VerifyResult verifyUser(int id, String password);

    void add(User user);

    List<Integer> getIdList();

    User getUserById(int id);

    List<User> findAll();

    User getUserByName(String username);

    void deleteInBatch();

    void changeState(int id);

    void update(User user);
}
