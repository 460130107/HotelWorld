package edu.nju.hotel.data.dao;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.util.VerifyResult;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/22.
 */

public interface HotelDao {
    VerifyResult verifyHotel(int id, String password);

    void add(Hotel user);

    Hotel getHotelById(int id);

    List<Hotel> findAll();

    Hotel getHotelByName(String hotelname);

    void changeState(int id);

    void update(Hotel Hotel);
}
