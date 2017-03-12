package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.model.*;
import edu.nju.hotel.logic.vo.*;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/3/3.
 */
public interface TransferService {
    UserVO transferUser(User user);
    ManagerVO transferAdmin(Admin admin);
    HotelVO transferHotel(Hotel hotel);

    List<RoomVO> transferRoomVOs(List<Room> list);
    RoomVO transferRoomVO(Room room);

    List<PlanVO> transferPlanVOs(List<Plan> list);

    List<RoomTypeVO> transferRoomTypeVOs(List<RoomType> list);
}
