package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.model.Admin;
import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.ManagerVO;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
public interface ManagerService {
    /**
     * 验证登陆信息
     * @param id  客栈编码
     * @param password   密码
     * @return  返回查找状态 {@link VerifyResult}
     */
    VerifyResult verifyLogin(int id, String password);

}
