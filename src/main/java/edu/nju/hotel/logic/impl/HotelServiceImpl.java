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

        hotel.setId(hotelId);
        hotelRepository.saveAndFlush(hotel);
        return transferService.transferHotel(hotel);
    }

    private int getRandomHotelId(){
        List<Integer> idList= hotelRepository.getIdList();
        Random random=new Random();
        int id=0;
        for (int i=0;i<1000000;i++){
            id=random.nextInt(1000000);
            if (!idList.contains(id)){
                return id;
            }
        }
        return id;
    }
}
