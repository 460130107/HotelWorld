package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update User us set us.idcard=:qIdcard, us.bank=:number, us.phone=:qPhone, us.psw=:qPassword where us.id=:qId")
    void updateUser(@Param("qIdcard") String idcard, @Param("number") String bankNumber,
                           @Param("qPhone") String phone, @Param("qPassword") String password, @Param("qId") Integer id);

    @Modifying
    @Transactional
    @Query("update User us set us.balance=:balance where us.id=:uId")
    void updateUserBalance(@Param("balance") int balance, @Param("uId") Integer id);

    @Modifying
    @Transactional
    @Query("update User us set us.state=:state where us.id=:uId")
    void updateUserState(@Param("state") Integer state,@Param("uId") Integer id);

}
