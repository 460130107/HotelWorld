package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    @Query("select rt from RoomType rt where rt.hotelByHotelId.id=?1")
    List<RoomType> getRoomTypeListById(int id);

}
