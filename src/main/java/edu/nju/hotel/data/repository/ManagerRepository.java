package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ManagerRepository extends JpaRepository<Admin, Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update Admin ad set ad.name=:aName, ad.psw=:psw where ad.id=:aId")
    void updateAdmin(@Param("aName") String aName, @Param("psw") String psw, @Param("aId") Integer aId);

    @Query("select ad from Admin ad where ad.name=:aName")
    List<Admin> getAdmin(@Param("aName") String aName);

}
