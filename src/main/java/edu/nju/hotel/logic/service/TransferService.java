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
    BookingVO transferBooking(Booking booking);

    List<RoomVO> transferRooms(List<Room> list);
    RoomVO transferRoom(Room room);

    List<PlanVO> transferPlans(List<Plan> list);

    List<RoomTypeVO> transferRoomTypes(List<RoomType> list);

    List<HotelVO> transferHotels(List<Hotel> hotelList);
}
