package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Checkin;
import edu.nju.hotel.logic.vo.CheckinVO;
import edu.nju.hotel.logic.vo.HotelBillVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Integer> {

    @Query("select ckin from Checkin ckin where ckin.roomTypeByRoomTypeId.hotelByHotelId.id=?1 and ckin.outTime>?2")
    List<Checkin> getCheckinListAfter(int hotelId, Date date);

    @Query("select ckin from Checkin ckin where ckin.roomTypeByRoomTypeId.hotelByHotelId.id=?1 order by ckin.inTime desc")
    List<Checkin> getByHotelId(int hotelid);

    @Query("select ckin from Checkin ckin where ckin.userByUserId.id=?1")
    List<Checkin> getByUserId(int userid);

    @Query("select ckin from Checkin ckin where ckin.payType=1 and ckin.payed=0")
    List<Checkin> getUnPayedCheckin();

    @Query("select count (ck.id) from Checkin ck where ck.inTime<?2 and ck.inTime>?3 and ck.roomTypeByRoomTypeId.hotelByHotelId.id=?1 group by ck.roomTypeByRoomTypeId.hotelByHotelId.id")
    Integer getCheckinNumByTime(int id, Date today, Date monthBefore);

    @Query("select sum (ck.price) from Checkin ck where ck.userByUserId.id is not null and ck.creatTime<?1 and ck.creatTime>?2 ")
    Integer getTotalConsumeByTime(Date today, Date dayBefore);

    @Query("select sum (ck.price) from Checkin ck where ck.creatTime>=?2 and ck.creatTime<?1")
    Integer getEarningByDay(Date date, Date date1);

    @Query("select sum (ck.price) from Checkin ck where ck.creatTime>=?2 and ck.creatTime<?1 and ck.roomTypeByRoomTypeId.hotelByHotelId.id=?3")
    Integer getEarningByDayByHotel(Date date, Date date1, int hotelid);

    @Query("select sum (ck.price) from Checkin ck where ck.creatTime>=?2 and ck.creatTime<?1 and ck.userByUserId.id=?3")
    Integer getConsumptionByTimeByUser(Date today, Date dayBefore, int uid);

    @Query("select count (ck.id) from Checkin ck where ck.creatTime>=?2 and ck.creatTime<?1 and ck.userByUserId.id=?3")
    Integer getTotalCheckinByTimeByUser(Date today, Date dayBefore, int uid);
}
