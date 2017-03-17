package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.vo.BookingVO;
import edu.nju.hotel.logic.vo.CheckinVO;
import edu.nju.hotel.logic.vo.UserVO;

import java.util.List;

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

    UserVO addUser(User user);

    UserVO getUserById(int id);

    String updateUser(UserVO user);

    String chargeCard(int id,int money);

    String logOff(int id);

    List<BookingVO> getBookingHistory(int userId);

    void cancelBooking(int id);

    void exchangePoints(int userid,int points);

    List<CheckinVO> getCheckinHistory(int userid);
}
