package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.dao.UserDao;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @Override
    public UserVO addUser(User user) {
        user.setId(getRandomUserId());
        userDao.add(user);
        UserVO vo=new UserVO();
        vo.setId(user.getId());
        return vo;
    }

    private int getRandomUserId(){
        List<Integer> idList= userDao.getIdList();
        Random random=new Random();
        int id=0;
        for (int i=0;i<9000000;i++){
            id=random.nextInt(9000000)+1000000;
            if (!idList.contains(id)){
                return id;
            }
        }
        return id;
    }
}
