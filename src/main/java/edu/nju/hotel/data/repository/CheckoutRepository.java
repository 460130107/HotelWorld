package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.Checkin;
import edu.nju.hotel.data.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

    @Query("select ckout from Checkout ckout where ckout.checkinByCheckinId.id=?1")
    List<Checkout> findByCheckinId(int id);
}
