package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.dao.HotelDao;
import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.repository.HotelRepository;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private TransferService transferService;

    @Autowired
    private HotelDao hotelDao;

    @Override
    public VerifyResult verifyLogin(int id, String password) {
        Hotel hotel=hotelRepository.findOne(id);

        if( hotel==null ){
            return VerifyResult.NOTEXIST;
        }
        else if(password.equals(hotel.getPsw())){
            return VerifyResult.SUCCESS;
        }
        return VerifyResult.INCORRECT;
    }

    @Override
    public HotelVO addHotel(Hotel hotel) {
        int hotelId=getRandomHotelId();
        hotel.setApproved(0);

        hotel.setId(hotelId);
        hotelRepository.saveAndFlush(hotel);
        return transferService.transferHotel(hotel);
    }

    @Override
    public HotelVO getHotelById(int id) {
        Hotel h=hotelRepository.findOne(id);
        return transferService.transferHotel(h);
    }

    @Override
    public String updateHotel(HotelVO hotel) {
        hotelRepository.updateHotel(hotel.getName(),hotel.getPsw(),hotel.getDescription(),hotel.getCity(),hotel.getLocation(),hotel.getId());
        return "";
    }

    @Override
    public int getRandomHotelId(){
        Hotel hotel;
        Random random=new Random();
        int id=0;
        for (int i=0;i<1000000;i++){
            id=random.nextInt(1000000);
            hotel= hotelRepository.findOne(id);
            if (hotel==null){
                return id+1000000;
            }
        }
        return id+1000000;
    }
}
