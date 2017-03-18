package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.HotelUpdate;
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
public interface HotelUpdateRepository extends JpaRepository<HotelUpdate, Integer> {

    @Query("select hu from HotelUpdate hu where hu.hotelByHotelId.id=?1")
    List<HotelUpdate> findByHotelId(int hotelid);

    @Query("select up from HotelUpdate up where up.approved=0")
    List<HotelUpdate> findAllUnApproved();
}
