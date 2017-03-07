package edu.nju.hotel.data.daoImpl;

import edu.nju.hotel.data.dao.HotelDao;
import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.util.VerifyResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
public class HotelDaoImpl implements HotelDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public VerifyResult verifyHotel(int id, String password) {
        return null;
    }

    @Override
    public void add(Hotel user) {

    }

    @Override
    public List<Integer> getIdList() {
        Query q=em.createQuery("select h.id from Hotel h");
        List<Integer> list=q.getResultList();
        return list;
    }

    @Override
    public Hotel getHotelById(int id) {
        return null;
    }

    @Override
    public List<Hotel> findAll() {
        return null;
    }

    @Override
    public Hotel getHotelByName(String hotelname) {
        return null;
    }

    @Override
    public void changeState(int id) {

    }

    @Override
    public void update(Hotel Hotel) {

    }
}
