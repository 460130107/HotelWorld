package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.dao.UserDao;
import edu.nju.hotel.data.model.*;
import edu.nju.hotel.data.repository.*;
import edu.nju.hotel.data.util.ChargeResult;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.BookingVO;
import edu.nju.hotel.logic.vo.CheckinVO;
import edu.nju.hotel.logic.vo.UserUpdate;
import edu.nju.hotel.logic.vo.UserVO;
import edu.nju.hotel.util.constant.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 验证登陆信息
     *
     * @param id       会员卡号
     * @param password 密码
     * @return 返回查找状态 {@link VerifyResult}
     */
    @Autowired
    UserDao userDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomAsignRepository roomAsignRepository;

    @Autowired
    private CheckinRepository checkinRepository;


    @Autowired
    TransferService transferService;

    @Override
    public VerifyResult verifyLogin(int id, String password) {
        return userDao.verifyUser(id,password);
    }

    @Override
    public UserVO addUser(User user) {
        user.setId(getRandomUserId());
        userDao.add(user);
        return transferService.transferUser(user);
    }

    @Override
    public UserVO getUserById(int id) {
        User user=userDao.getUserById(id);
        return transferService.transferUser(user);
    }

    @Override
    public String updateUser(UserVO user) {
        userRepository.updateUser(user.getIdcard(),user.getBank(),user.getPhone(),user.getPsw(),user.getId());

        return "";
    }

    @Override
    public String chargeCard(int id,int money) {
        BankCard bank=userDao.getBankCardByUser(id) ;
        if(bank==null){
            return ChargeResult.WRONGCARD.toString();
        }
        else if(bank.getBalance()<money) {
            return ChargeResult.NOTENOUGH.toString();
        }
        else {

            int balance=bank.getBalance()-money;
            User user=userDao.getUserById(id);
            if(money>=1000&&user.getState()==0){
                userRepository.updateUserState(1,user.getId());
            }
            int userBls=user.getBalance()+money;
            userRepository.updateUserBalance(userBls,id);
            bankRepository.updateBankCard(bank.getNumber(),balance);
            return ChargeResult.SUCCESS.toString();
        }
    }

    @Override
    public String logOff(int id) {
        userRepository.updateUserState(4,id);
        return "success";
    }

    @Override
    public List<BookingVO> getBookingHistory(int userId) {
        List<Booking> bookingList=bookingRepository.getUserHistory(userId);

        return transferService.transferBookings(bookingList);
    }

    @Override
    public void cancelBooking(int id) {
        Booking booking=bookingRepository.findOne(id);
        bookingRepository.updateCancel(booking.getId());
        List<RoomAsign> asigns=  roomAsignRepository.findByBookingId(id);
        for (RoomAsign roomAsign:asigns){
            roomAsignRepository.delete(roomAsign.getId());
        }
        userRepository.updateUserBalance(booking.getUserByUserId().getBalance()+200,booking.getUserByUserId().getId());
    }

    @Override
    public void exchangePoints(int userid,int points) {
        User user=userRepository.findOne(userid);
        int userPoints=user.getPoints();
        user.setPoints(userPoints-points);
        int incre=points/ UserConstant.POINTS_TO_BALANCE;
        user.setBalance(user.getBalance()+incre);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<CheckinVO> getCheckinHistory(int userid) {
        List<Checkin> checkinList=checkinRepository.getByUserId(userid);
        return transferService.transferCheckins(checkinList);
    }


    /**
     * 自动生成用户id
     * 2000000之后的都是用户id
     * @return 返回用户id {@link int}
     */
    private int getRandomUserId(){
        List<Integer> idList= userDao.getIdList();
        Random random=new Random();
        int id=0;
        for (int i=0;i<8000000;i++){
            id=random.nextInt(8000000)+2000000;
            if (!idList.contains(id)){
                return id;
            }
        }
        return id;
    }
}
