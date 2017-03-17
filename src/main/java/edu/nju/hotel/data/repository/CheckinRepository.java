package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Checkin;
import edu.nju.hotel.logic.vo.CheckinVO;
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
}
