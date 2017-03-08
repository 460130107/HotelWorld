package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.model.Admin;
import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.ManagerVO;
import edu.nju.hotel.logic.vo.UserVO;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxiaofan on 2017/3/3.
 */
@Component
public class TransferServiceImpl implements TransferService {
    @Override
    public UserVO transferUser(User user) {
        UserVO vo=new UserVO();
        vo.setId(user.getId());
        vo.setName(user.getName());
        vo.setPsw(user.getPsw());
        vo.setBank(user.getBank());
        vo.setLevel(user.getLevel());
        vo.setCreatTime(user.getCreatTime());
        vo.setPhone(user.getPhone());
        vo.setPoints(user.getPoints());
        vo.setIdcard(user.getIdcard());
        vo.setBalance(user.getBalance());
        vo.setState(user.getState());
        return vo;
    }

    @Override
    public ManagerVO transferAdmin(Admin admin) {
        ManagerVO vo=new ManagerVO();
        vo.setId(admin.getId());
        vo.setPsw(admin.getPsw());
        vo.setName(admin.getName());
        return vo;
    }

    @Override
    public HotelVO transferHotel(Hotel hotel) {
        HotelVO vo=new HotelVO();
        vo.setId(hotel.getId());
        vo.setName(hotel.getName());
        vo.setPsw(hotel.getPsw());
        vo.setApproved(hotel.getApproved());
        vo.setCity(hotel.getCity());
        vo.setLocation(hotel.getLocation());
        vo.setDescription(hotel.getDescription());
        return vo;
    }
}
