package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.util.VerifyResult;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
public interface ManagerService {
    /**
     * 验证登陆信息
     * @param name  经理名字
     * @param password   密码
     * @return  返回查找状态 {@link VerifyResult}
     */
    VerifyResult verifyLogin(String name, String password);

}
