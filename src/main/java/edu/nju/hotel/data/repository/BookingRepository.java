package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Booking;
import edu.nju.hotel.data.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
