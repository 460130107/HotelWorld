package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.Room;
import edu.nju.hotel.data.model.RoomType;
import edu.nju.hotel.logic.vo.RoomVO;
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
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    @Query("select rt from RoomType rt where rt.hotelByHotelId.id=?1")
    List<RoomType> getRoomTypeListById(int id);

}
