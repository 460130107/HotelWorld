package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.model.*;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.PlanVO;
import edu.nju.hotel.logic.vo.RoomTypeVO;
import edu.nju.hotel.logic.vo.RoomVO;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

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

    HotelVO addHotel(Hotel hotel);

    HotelVO getHotelById(int id);

    String updateHotel(HotelVO hotel);

    void addRoomType(String roomtype,int id);

    List<RoomTypeVO> getRoomType(int hotelid);

    void addRoom(Room room);

    ModelMap getRoom(int hotelid);

    void addPlan(Plan plan);

    List<PlanVO> getPlan(int hotelid);

}
