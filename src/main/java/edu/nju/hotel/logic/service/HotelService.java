package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.vo.HotelVO;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
public interface HotelService {
    /**
     * 验证登陆信息
     * @param id  客栈编码
     * @param password   密码
     * @return  返回查找状态 {@link VerifyResult}
     */
    VerifyResult verifyLogin(int id, String password);

    HotelVO addHotel(Hotel user);

}
