package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.dao.HotelDao;
import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.Plan;
import edu.nju.hotel.data.model.Room;
import edu.nju.hotel.data.model.RoomType;
import edu.nju.hotel.data.repository.HotelRepository;
import edu.nju.hotel.data.repository.PlanRepository;
import edu.nju.hotel.data.repository.RoomRepository;
import edu.nju.hotel.data.repository.RoomTypeRepository;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.PlanVO;
import edu.nju.hotel.logic.vo.RoomTypeVO;
import edu.nju.hotel.logic.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PlanRepository planRepository;

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
    public void addRoomType(String roomtype, int id) {
        RoomType roomType=new RoomType();
        roomType.setName(roomtype);
        roomType.setHotelByHotelId(hotelRepository.findOne(id));
        roomTypeRepository.saveAndFlush(roomType);
    }

    @Override
    public List<RoomTypeVO> getRoomType(int hotelid) {
        List<RoomType> roomTypeList=roomTypeRepository.getRoomTypeListById(hotelid);
        List<RoomTypeVO> roomVOS=transferService.transferRoomTypeVOs(roomTypeList);
        return roomVOS;
    }

    @Override
    public void addRoom(Room room) {
        roomRepository.saveAndFlush(room);
    }

    @Override
    public ModelMap getRoom(int hotelid) {
        List<RoomType> roomTypeList=roomTypeRepository.getRoomTypeListById(hotelid);
        ModelMap map=new ModelMap();
        for (RoomType roomType:roomTypeList){
            List<Room> list=roomRepository.getRoomList(hotelid,roomType.getId());

            if(list.size()!=0){
                List<RoomVO> roomVOS=transferService.transferRoomVOs(list);
                map.addAttribute(roomType.getName(),roomVOS);
            }
        }
        return map;
    }

    @Override
    public void addPlan(Plan plan) {
        planRepository.saveAndFlush(plan);
    }

    @Override
    public List<PlanVO> getPlan(int hotelid) {
        List<Plan> list=planRepository.getPlanList(hotelid);
        List<PlanVO> planVOS=transferService.transferPlanVOs(list);
        return planVOS;
    }

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
