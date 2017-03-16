package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Hotel;
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
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update Hotel ht set ht.name=?1, ht.psw=?2,ht.description=?3,ht.city=?4,ht.location=?5 where ht.id=?6 ")
    void updateHotel(String aName, String psw, String description,String city,String location,int id);

    @Query("select h.id from Hotel h")
    List<Integer> getIdList();

    List<RoomType> getRoomTypeById(int id);

}
