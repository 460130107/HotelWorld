package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.model.UserPause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface UserPauseRepository extends JpaRepository<UserPause, Integer> {


    @Query("select u from UserPause u where u.userid=?1")
    UserPause findByUser(int id);
}
