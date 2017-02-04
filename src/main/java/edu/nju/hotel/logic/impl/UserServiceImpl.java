package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.dao.UserDao;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 验证登陆信息
     *
     * @param id       会员卡号
     * @param password 密码
     * @return 返回查找状态 {@link VerifyResult}
     */
    @Autowired
    UserDao userDao;


    @Override
    public VerifyResult verifyLogin(int id, String password) {
        return userDao.verifyUser(id,password);
    }
}
