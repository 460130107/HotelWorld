package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.model.User;
import edu.nju.hotel.logic.service.TransferService;
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
}
