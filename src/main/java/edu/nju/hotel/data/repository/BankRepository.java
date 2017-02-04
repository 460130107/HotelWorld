package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by dzkan on 2016/3/8.
 */
@org.springframework.stereotype.Repository
public interface BankRepository extends JpaRepository<BankCard, Integer> {

//    @Modifying      // 说明该方法是修改操作
//    @Transactional  // 说明该方法是事务性操作
//    // 定义查询
//    // @Param注解用于提取参数
//    @Query("update UserEntity us set us.nickname=:qNickname, us.firstName=:qFirstName, us.lastName=:qLastName, us.password=:qPassword where us.id=:qId")
//    public void updateUser(@Param("qNickname") String nickname, @Param("qFirstName") String firstName,
//                           @Param("qLastName") String qLastName, @Param("qPassword") String password, @Param("qId") Integer id);
}
