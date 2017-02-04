package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.util.VerifyResult;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
public interface UserService {
    /**
     * 验证登陆信息
     * @param id  会员卡号
     * @param password   密码
     * @return  返回查找状态 {@link VerifyResult}
     */
    VerifyResult verifyLogin(int id, String password);


}
