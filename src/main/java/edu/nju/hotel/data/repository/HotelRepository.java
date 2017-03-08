package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("update Hotel ht set ht.name=:aName, ht.psw=:psw where ht.id=:aId")
    void updateHotel(@Param("aName") String aName, @Param("psw") String psw, @Param("aId") Integer aId);

    @Query("select h.id from Hotel h")
    List<Integer> getIdList();

}
