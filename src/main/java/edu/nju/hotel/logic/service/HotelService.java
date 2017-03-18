package edu.nju.hotel.logic.service;

import edu.nju.hotel.data.model.*;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.vo.*;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
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

    HotelUpdateVO updateHotel(HotelUpdateVO hotelUpdateVO);

    void addRoomType(String roomtype,int id);

    List<RoomTypeVO> getRoomType(int hotelid);

    void addRoom(int typeid,String roomName);

    ModelMap getRoom(int hotelid);

    void addPlan(int hotelid,String start,String end,int typeid,int price);

    List<PlanVO> getPlan(int hotelid);

    List<HotelVO> getHotelList();

    ModelMap getSpareRoom(int id, String start, String end);

    void bookHotel(BookingVO bookingVO);

    List<BookingVO> getBookingNow(int hotelId);

    BookingVO getBookingById(int bookingId);

    ModelMap checkinBooking(int bookingId,int payType,String idCards);

    ModelMap newCheckin(CheckinListVO checkinListVO);

    List<CheckinVO> getCheckinListNotOut(int hotelId);

    void checkout(int checkinId);

    List<BookingVO> getBookingHistoryByHotel(int hotelid);

    List<CheckinVO> getHistoryByHotel(int hotelid);

    List<HotelVO> getUnApprovedHotels();

    List<HotelUpdateVO> getHotelUpdates(int hotelid);

    List<HotelUpdateVO> getUnApprovedHotelUpdates();

    void approveUpdate(int updateId);

    void disapproveUpdate(int updateId);

    void approveHotel(int hotelId);

    void disapproveHotel(int hotelId);

    List<CheckinVO> getUnpayedBills();

    HotelVO payHotel(int checkinId);
}
