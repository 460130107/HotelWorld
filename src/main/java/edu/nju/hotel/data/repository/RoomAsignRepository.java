package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.RoomAsign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface RoomAsignRepository extends JpaRepository<RoomAsign, Integer> {
    @Query("select r.roomByRoomId.id from RoomAsign r where r.roomByRoomId.roomTypeByRoomTypeId.id=?1 and ((r.inTime>?2 and r.inTime<?3) or (r.outTime>?2 and r.outTime<?3))")
    List<Integer> getAssignRoomBetween(int id, Date start, Date end);

}
