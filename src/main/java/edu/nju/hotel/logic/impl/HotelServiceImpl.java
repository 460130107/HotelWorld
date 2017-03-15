package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.dao.HotelDao;
import edu.nju.hotel.data.model.*;
import edu.nju.hotel.data.repository.*;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.sql.Timestamp;
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
    private UserRepository userRepository;

    @Autowired
    private RoomAsignRepository roomAsignRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private TransferService transferService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CheckinRepository checkinRepository;



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
    public void bookHotel(BookingVO bookingVO) {
        Hotel hotel=hotelRepository.findOne(bookingVO.getHotelId());
        RoomType roomType=roomTypeRepository.findOne(bookingVO.getRoomTypeId());
        User user=userRepository.findOne(bookingVO.getUserId());

        Booking booking=getBooking(bookingVO,hotel,roomType,user);
        Booking bookingSaved=bookingRepository.saveAndFlush(booking);

        List<Room> spareRooms=getSpareRoomList(roomType.getHotelByHotelId().getId(),roomType.getId(),bookingVO.getInTime(),bookingVO.getOutTime());

        String[] usernames=booking.getNameinfo().trim().split(" ");
        int roomNum=bookingVO.getRoomNum();//3
        int userNum=usernames.length;//5
        int pointer=0;
        for(int i=0;i<roomNum;i++){
            RoomAsign roomAsign=null;
            //一间房2个人的情况
            if(i<userNum%roomNum||(userNum==2&&roomNum==1)){
                roomAsign=creatRoomAssign(roomType,bookingSaved,usernames[pointer]+" "+usernames[pointer+1],spareRooms.get(i));
                pointer+=2;
            }
            else {
                roomAsign=creatRoomAssign(roomType,bookingSaved,usernames[pointer],spareRooms.get(i));
                pointer++;
            }
            roomAsignRepository.saveAndFlush(roomAsign);
        }
        userRepository.updateUserBalance(user.getBalance()-200,user.getId());
    }

    @Override
    public List<BookingVO> getBookingNow(int hotelId) {
        Date date=new Date();
        date.setDate(date.getDate()-1);
        List<Booking> bookingList=bookingRepository.getBookingAfter(hotelId,date);

        return transferService.transferBookings(bookingList);
    }

    @Override
    public BookingVO getBookingById(int bookingId) {
        Booking booking=bookingRepository.findOne(bookingId);
        return transferService.transferBooking(booking);
    }


    @Override
    public ModelMap checkinBooking(int bookingId, int payType,String idCards) {
        ModelMap result=new ModelMap();
        Booking booking=bookingRepository.findOne(bookingId);

        String[] idCardList=idCards.split(" ");
        if(payType==1){
            User user=booking.getUserByUserId();
            int userBal=user.getBalance();
            if(userBal<booking.getPrice()){
                result.addAttribute("error","用户余额为"+userBal+"，支付不足");
                return result;
            }
            else userRepository.updateUserBalance(userBal-booking.getPrice(),user.getId());
        }
        List<RoomAsign> roomAsigns=roomAsignRepository.findByBookingId(bookingId);
        int assignIndex=0;
        for(RoomAsign roomAsign:roomAsigns){
            String user2=roomAsign.getUser2();
            if (user2.length()>1){
                roomAsignRepository.updateUserCard(idCardList[assignIndex],idCardList[assignIndex+1],roomAsign.getId());
            }
            else {
                roomAsignRepository.updateUserCard(idCardList[assignIndex],"",roomAsign.getId());
            }
            roomAsignRepository.updateAssignChekin(roomAsign.getId());
        }
        bookingRepository.updateChecked(booking.getId());
        Checkin checkin=creatCheckin(booking);
        checkin.setPayType(payType);
        checkinRepository.saveAndFlush(checkin);
        List<RoomAssignVO> asgVOS=transferService.transferRoomAssigns(roomAsigns);
        result.addAttribute("roomAssign",asgVOS);

        return result;
    }



    private Checkin creatCheckin(Booking booking) {
        Checkin checkin=new Checkin();
        checkin.setPrice(booking.getPrice());
        checkin.setBookingByBookId(booking);
        checkin.setCheckoutsById(null);
        checkin.setUserByUserId(booking.getUserByUserId());
        return checkin;
    }

    private RoomAsign creatRoomAssign(RoomType roomType, Booking booking, String userName, Room spareRoom) {
        String[] userNames=userName.split(" ");
        RoomAsign roomAsign=new RoomAsign();
//        空余的房子
        roomAsign.setRoomByRoomId(spareRoom);
        roomAsign.setInTime(booking.getInTime());
        roomAsign.setOutTime(booking.getOutTime());
        roomAsign.setBookingByBookId(booking);
        roomAsign.setCheckinByCheckinId(booking.getCheckinsById());
        roomAsign.setUser1(userNames[0]);
        if(userNames.length>1){
            roomAsign.setUser2(userNames[1]);
        }
        return roomAsign;
    }

    private Booking getBooking(BookingVO bookingVO,Hotel hotel,RoomType roomType,User user){
        Booking booking=new Booking();
        booking.setHotelByHotelId(hotel);
        booking.setCancled(bookingVO.getCancled());
        booking.setCreatTime(bookingVO.getCreatTime());
        booking.setDeposit(bookingVO.getDeposit());
        booking.setEmail(bookingVO.getEmail());
        booking.setInTime(getDate(bookingVO.getInTime()));
        booking.setOutTime(getDate(bookingVO.getOutTime()));
        booking.setCreatTime(bookingVO.getCreatTime());
        booking.setCheckinsById(null);
        booking.setRoomNum(bookingVO.getRoomNum());
        booking.setPhone(bookingVO.getPhone());
        booking.setNameinfo(bookingVO.getNameinfo().trim());
        booking.setPrice(bookingVO.getPrice());
        booking.setRoomTypeByRoomTypeId(roomType);
        booking.setUserByUserId(user);
        return booking;
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
            mapType.addAttribute("id",roomType.getId());
            map.addAttribute(roomType.getName(),mapType);
        }
        return map;
    }

    private List<Room> getSpareRoomList(int hotelid, int typeid, String start, String end) {
        List<Room> list=roomRepository.getRoomList(hotelid,typeid);
        List<Integer> assignRooms=roomAsignRepository.getAssignRoomBetween(typeid,getDate(start),getDate(end));
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
