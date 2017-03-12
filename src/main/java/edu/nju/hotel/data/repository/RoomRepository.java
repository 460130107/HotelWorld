package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.Room;
import edu.nju.hotel.data.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("select room from Room room where room.roomTypeByRoomTypeId.hotelByHotelId.id=?1 and room.roomTypeByRoomTypeId.id=?2")
    List<Room> getRoomList(int hotelid,int roomtypeid);

}
