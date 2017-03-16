package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("select b from Booking b where b.roomTypeByRoomTypeId.id=?1 and (b.inTime>?2 and b.inTime<?3) or (b.outTime>?2 and b.outTime<?3)")
    List<Booking> getBookingListBetween(int id,Date start,Date end);

    @Query("select b from Booking b where b.creatTime=?1")
    Booking findByCreatTime(Timestamp creatTime);

    @Query("select b from Booking b where b.userByUserId.id=?1 order by b.inTime")
    List<Booking> getUserHistory(int userId);

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    @Query("update Booking bk set bk.cancled=1 where bk.id=?1")
    void updateCancel(int id);

    @Query("select bk from Booking bk where bk.hotelByHotelId.id=?1 and bk.inTime>?2 and bk.cancled=0 and bk.checked=0")
    List<Booking> getBookingAfter(int hotelId, Date date);

    @Modifying
    @Transactional
    @Query("update Booking bk set bk.checked=1 where bk.id=?1")
    void updateChecked(int id);
}
