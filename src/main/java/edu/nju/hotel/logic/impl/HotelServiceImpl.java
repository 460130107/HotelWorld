package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.dao.HotelDao;
import edu.nju.hotel.data.model.*;
import edu.nju.hotel.data.repository.*;
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

import java.util.*;

import static java.lang.Integer.parseInt;

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
    private RoomAsignRepository roomAsignRepository;

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
        List<RoomTypeVO> roomVOS=transferService.transferRoomTypes(roomTypeList);
        return roomVOS;
    }

    @Override
    public void addRoom(int typeid, String roomName) {
        Room room=new Room();
        RoomType roomType=roomTypeRepository.findOne(typeid);
        room.setRoomTypeByRoomTypeId(roomType);
        room.setName(roomName);
        roomRepository.saveAndFlush(room);
    }


    @Override
    public ModelMap getRoom(int hotelid) {
        List<RoomType> roomTypeList=roomTypeRepository.getRoomTypeListById(hotelid);
        ModelMap map=new ModelMap();
        for (RoomType roomType:roomTypeList){
            List<Room> list=roomRepository.getRoomList(hotelid,roomType.getId());
            if(list.size()!=0){
                List<RoomVO> roomVOS=transferService.transferRooms(list);
                map.addAttribute(roomType.getName(),roomVOS);
            }
            else {
                map.addAttribute(roomType.getName(),new ArrayList<RoomVO>());
            }
        }
        return map;
    }

    private int getCurrentPrice(RoomType roomType) {
        Date now=new Date();
//        Collection plans=roomType.getPlansById();
        List<Plan> plans=planRepository.getCurrentPrice(roomType.getId(),now);
        int size=plans.size();
        if(size==0){
            return -1;
        }
        return plans.get(0).getPrice();
    }

    @Override
    public void addPlan(int hotelid, String start, String end, int typeid, int price) {
        Plan plan=new Plan();
        RoomType roomType=roomTypeRepository.findOne(typeid);
        Hotel hotel=hotelRepository.findOne(hotelid);
        plan.setPrice(price);

        plan.setStartTime(getDate(start));
        plan.setEndTime(getDate(end));
        plan.setHotelByHotelId(hotel);
        plan.setRoomTypeByRoomTypeId(roomType);
        planRepository.saveAndFlush(plan);

    }

    private Date getDate(String dataString){
        int year=parseInt(dataString.substring(0,4))-1900;
        int month=parseInt(dataString.substring(5,7));
        int day=parseInt(dataString.substring(8,10));
        return new Date(year,month,day);
    }


    @Override
    public List<PlanVO> getPlan(int hotelid) {
        List<Plan> list=planRepository.getPlanList(hotelid);
        List<PlanVO> planVOS=transferService.transferPlans(list);
        return planVOS;
    }

    @Override
    public List<HotelVO> getHotelList() {
        List<Hotel> hotelList=hotelRepository.findAll();
        List<HotelVO> hotels=transferService.transferHotels(hotelList);
        return hotels;
    }

    @Override
    public ModelMap getSpareRoom(int hotelid, String start, String end) {
        List<RoomType> roomTypeList=roomTypeRepository.getRoomTypeListById(hotelid);
        ModelMap map=new ModelMap();
        for (RoomType roomType:roomTypeList){
//            这里和上面不同，获取的是空闲的房间
            List<Room> list=getSpareRoomList(hotelid,roomType.getId(),start,end);

            ModelMap mapType=new ModelMap();
            if(list.size()!=0){
                List<RoomVO> roomVOS=transferService.transferRooms(list);
                mapType.addAttribute("list",roomVOS);
            }
            else {
                mapType.addAttribute("list",new ArrayList<RoomVO>());
            }
            int price=getCurrentPrice(roomType);
            mapType.addAttribute("price",price);
            map.addAttribute(roomType.getName(),mapType);
        }
        return map;
    }

    private List<Room> getSpareRoomList(int hotelid, int id, String start, String end) {
        List<Room> list=roomRepository.getRoomList(hotelid,id);
        List<Integer> assignRooms=roomAsignRepository.getAssignRoomBetween(id,getDate(start),getDate(end));
        List<Room> result=new ArrayList<>();
        for (Room room : list){
            if(!assignRooms.contains(room.getId())){
                result.add(room);
            }
        }
        return result;
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
