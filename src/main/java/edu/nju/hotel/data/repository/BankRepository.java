package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by dzkan on 2016/3/8.
 */
@org.springframework.stereotype.Repository
public interface BankRepository extends JpaRepository<BankCard, Integer> {

    @Modifying
    @Transactional
    @Query("update BankCard bank set bank.balance=:money where bank.number=:cardNum")
    void updateBankCard(@Param("cardNum") String number, @Param("money") int money);
}
