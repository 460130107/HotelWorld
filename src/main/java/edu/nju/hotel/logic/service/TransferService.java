package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.model.Admin;
import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.ManagerVO;
import edu.nju.hotel.logic.vo.UserVO;

/**
 * Created by zhouxiaofan on 2017/3/3.
 */
public interface TransferService {
    UserVO transferUser(User user);
    ManagerVO transferAdmin(Admin admin);
    HotelVO transferHotel(Hotel hotel);

}
