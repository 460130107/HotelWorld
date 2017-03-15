package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.RoomAsign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface RoomAsignRepository extends JpaRepository<RoomAsign, Integer> {
    @Query("select r.roomByRoomId.id from RoomAsign r where r.roomByRoomId.roomTypeByRoomTypeId.id=?1 and r.id not in (select r2.id from RoomAsign r2 where r2.inTime>=?3 or r2.outTime<=?2)")
    List<Integer> getAssignRoomBetween(int id, Date start, Date end);

    @Modifying
    @Transactional
    @Query("update RoomAsign asg set asg.state=1 where asg.id=?1")
    void updateAssignChekin(int id);

    @Modifying
    @Transactional
    @Query("update RoomAsign asg set asg.idcard1=?1 where asg.idcard2=?2")
    void updateUserCard(String i, String i1);
}
