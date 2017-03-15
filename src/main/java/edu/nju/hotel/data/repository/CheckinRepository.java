package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Booking;
import edu.nju.hotel.data.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Integer> {

}
