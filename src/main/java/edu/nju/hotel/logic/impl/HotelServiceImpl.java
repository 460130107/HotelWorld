package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.model.*;
import edu.nju.hotel.data.repository.*;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.vo.*;
import edu.nju.hotel.util.constant.HotelConstant;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
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
    private HotelUpdateRepository hotelUpdateRepository;

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

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private BankRepository bankRepository;


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

    /**
     * 客栈申请修改信息
     * @param hotelUpdateVO
     * @return
     */
    @Override
    public HotelUpdateVO updateHotel(HotelUpdateVO hotelUpdateVO) {
        Hotel hotel=hotelRepository.findOne(hotelUpdateVO.getHotelId());
        hotelUpdateVO.setName(hotel.getName());

        HotelUpdate update=creatUpdate(hotelUpdateVO,hotel);
        hotelUpdateRepository.save(update);

        String time =transferService.transTimestampToString(update.getCreatTime());
        hotelUpdateVO.setCreatTime(time);
        return hotelUpdateVO;
    }

    private HotelUpdate creatUpdate(HotelUpdateVO vo,Hotel hotel) {
        HotelUpdate update=new HotelUpdate();
        update.setLocation(vo.getLocation());
        update.setCity(vo.getCity());
        update.setDescription(vo.getDescription());
        update.setName(vo.getName());
        update.setPsw(vo.getRealPsw());
        update.setHotelByHotelId(hotel);
        return update;
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

        int roomNum=bookingVO.getRoomNum();
        for(int i=0;i<roomNum;i++){
            RoomAsign roomAsign=creatRoomAssign(roomType,bookingSaved,spareRooms.get(i));
            roomAsignRepository.saveAndFlush(roomAsign);
        }
        userRepository.updateUserBalance(user.getBalance()-200,user.getId());
    }

    @Override
    public List<BookingVO> getBookingNow(int hotelId) {
        Date date=getDayBefore(1);
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
        String[] userNameList=booking.getNameinfo().split(" ");
        User user=booking.getUserByUserId();
        //会员卡支付,扣费
        if(payType==1){
            int userBal=user.getBalance();
            if(userBal<booking.getPrice()){
                result.addAttribute("error","用户余额为"+userBal+"，支付不足");
                return result;
            }
            else {
                user.setBalance(userBal-booking.getPrice());

            }
        }
        user.setLevel(user.getLevel()+booking.getPrice());
        userRepository.saveAndFlush(user);
        //获取之前分配的房间
        List<RoomAsign> roomAsigns=roomAsignRepository.findByBookingId(bookingId);
        //返回房间分配结果
        List<RoomAssignVO> asgVOS=new ArrayList<>();
        int index=0;
        int roomNum=booking.getRoomNum();//3
        int userNum=userNameList.length;//5
        for(int i=0;i<roomNum;i++){
            String userName="";
            //存入住的订单
            Checkin checkin=creatCheckin(booking);
            checkin.setIdcard1(idCardList[index]);
            checkin.setUser1(userNameList[index]);
            userName+=idCardList[index]+" ";
            index++;
            //一间房2个人的情况
            if(userNum/roomNum==2||i<userNum%roomNum){
                checkin.setIdcard2(idCardList[index]);
                checkin.setUser2(userNameList[index]);
                userName+=idCardList[index]+" ";
                index++;
            }
            checkin.setPayType(payType);
            checkinRepository.saveAndFlush(checkin);

            //改变房间分配状态
            RoomAsign roomAsign=roomAsigns.get(i);
            roomAsignRepository.updateAssignChekin(roomAsign.getId(),checkin.getId());
            RoomAssignVO rsVO=transferService.transferRoomAssign(roomAsign);
            rsVO.setUser1(userName);
            asgVOS.add(rsVO);

        }
        //更新预订订单的状态为已入住
        bookingRepository.updateChecked(booking.getId());

        //返回房间分配结果
        result.addAttribute("roomAssign",asgVOS);
        return result;
    }

    @Override
    public ModelMap newCheckin(CheckinListVO checkinListVO) {
        ModelMap result=new ModelMap();
        User user=null;
        int userid=checkinListVO.getUserId();
        if(userid!=0){
            user=userRepository.findOne(userid);
        }
        //用户id输错了
        if(userid!=0&&user==null){
            result.addAttribute("error","用户不存在");
            return result;
        }
        int payType=checkinListVO.getPayType();
        if(payType==1){
            //用户没有输入id但是要会员卡支付
            if(user==null){
                result.addAttribute("error","没有输入id");
                return result;
            }
            //余额不足
            if(user.getBalance()<checkinListVO.getPrice()){
                result.addAttribute("error","余额不足");
                return result;
            }
            //扣房费
            user.setBalance(user.getBalance()-checkinListVO.getPrice());
        }
        if(user!=null){
            //加积分，加等级
            user.setPoints(user.getPoints()+checkinListVO.getPrice());
            user.setLevel(user.getLevel()+checkinListVO.getPrice());
            userRepository.saveAndFlush(user);
        }

        //遍历checkinList，建立每个checkin和对应的assign
        List<NewCheckinVO> checkinList=checkinListVO.getCheckinList();
        //返回房间分配结果
        List<RoomAssignVO> asgVOS=new ArrayList<>();
        for (NewCheckinVO vo:checkinList){
            RoomType roomType=roomTypeRepository.findOne(vo.getRoomTypeId());

            //存入住订单
            Checkin checkin=creatNewCheckin(vo);
            checkin.setUserByUserId(user);
            checkin.setPayType(payType);
            checkin.setRoomTypeByRoomTypeId(roomType);
            checkinRepository.saveAndFlush(checkin);

            List<Room> spareRooms=getSpareRoomList(roomType.getHotelByHotelId().getId(),roomType.getId(),vo.getInTime(),vo.getOutTime());
            RoomAsign roomAsign=creatNewRoomAssign(vo,spareRooms.get(0),checkin.getId());

            roomAsignRepository.saveAndFlush(roomAsign);

            RoomAssignVO assignVO=transferService.transferRoomAssign(roomAsign);
            assignVO.setUser1(checkin.getUser1());
            assignVO.setUser2(checkin.getUser2());
            asgVOS.add(assignVO);
        }
        result.addAttribute("roomAssign",asgVOS);
        return result;
    }

    @Override
    public List<CheckinVO> getCheckinListNotOut(int hotelId) {
        Date date=getDayBefore(1);
        List<Checkin> checkins=checkinRepository.getCheckinListAfter(hotelId,date);
        List<Checkin> result=new ArrayList<>();
        for (Checkin checkin:checkins){
            List<Checkout> checkoutList=checkoutRepository.findByCheckinId(checkin.getId());
            if(checkoutList.size()==0){
                result.add(checkin);
            }
        }
        return transferService.transferCheckins(result);
    }

    @Override
    public void checkout(int checkinId) {

        //如果是预订的就返回押金，删除房间分配信息，生成check out的订单
        Checkin checkin=checkinRepository.findOne(checkinId);
        List<RoomAsign> roomAsigns=roomAsignRepository.findByCheckin(checkin.getId());

        Checkout checkout=new Checkout();
        for (RoomAsign roomAsign:roomAsigns){
            checkout.setRoomAsignId(roomAsign.getId());
            roomAsignRepository.delete(roomAsign.getId());

        }
        if (checkin.getBookingByBookId()!=null){
            User user=checkin.getUserByUserId();
            userRepository.updateUserBalance(user.getBalance()+ HotelConstant.DEPOSIT,user.getId());
        }
        checkout.setCheckinByCheckinId(checkin);
        checkoutRepository.saveAndFlush(checkout);

    }

    @Override
    public List<BookingVO> getBookingHistoryByHotel(int hotelid) {
        List<Booking> bookings=bookingRepository.getByHotelId(hotelid);
        return transferService.transferBookings(bookings);
    }

    @Override
    public List<CheckinVO> getHistoryByHotel(int hotelid) {
        List<Checkin> checkinList=checkinRepository.getByHotelId(hotelid);
        return transferService.transferCheckins(checkinList);
    }

    @Override
    public List<HotelVO> getUnApprovedHotels() {
        List<Hotel> hotelList=hotelRepository.findAllUnApproved();
        return transferService.transferHotels(hotelList);
    }

    @Override
    public List<HotelUpdateVO> getHotelUpdates(int hotelid) {
        List<HotelUpdate> hotelUpdateList=hotelUpdateRepository.findByHotelId(hotelid);
        return transferService.transferHotelUpdates(hotelUpdateList);
    }

    @Override
    public List<HotelUpdateVO> getUnApprovedHotelUpdates() {
        List<HotelUpdate> list=hotelUpdateRepository.findAllUnApproved();
        return transferService.transferHotelUpdates(list);
    }

    @Override
    public void approveUpdate(int updateId) {
        HotelUpdate up=hotelUpdateRepository.findOne(updateId);
        up.setApproved(1);
        hotelUpdateRepository.saveAndFlush(up);
        updateHotelInfo(up.getHotelByHotelId().getId(),up);
    }

    private void updateHotelInfo(int hotelId, HotelUpdate up) {
        Hotel hotel=hotelRepository.findOne(hotelId);
        hotel.setPsw(up.getPsw());
        hotel.setDescription(up.getDescription());
        hotel.setLocation(up.getLocation());
        hotel.setCity(up.getCity());
        hotel.setName(up.getName());
        hotelRepository.saveAndFlush(hotel);
    }

    @Override
    public void disapproveUpdate(int updateId) {
        HotelUpdate up=hotelUpdateRepository.findOne(updateId);
        up.setApproved(2);
        hotelUpdateRepository.saveAndFlush(up);
    }

    @Override
    public void approveHotel(int hotelId) {
        Hotel hotel=hotelRepository.findOne(hotelId);
        hotel.setApproved(1);
        hotelRepository.saveAndFlush(hotel);
    }

    @Override
    public void disapproveHotel(int hotelId) {
        Hotel hotel=hotelRepository.findOne(hotelId);
        hotel.setApproved(2);
        hotelRepository.saveAndFlush(hotel);
    }

    @Override
    public List<CheckinVO> getUnpayedBills() {
        List<Checkin> checkinList=checkinRepository.getUnPayedCheckin();
        return transferService.transferCheckins(checkinList);
    }

    @Override
    public HotelVO payHotel(int checkinId) {
        Checkin checkin=checkinRepository.findOne(checkinId);
        Hotel hotel=hotelRepository.findOne(checkin.getRoomTypeByRoomTypeId().getHotelByHotelId().getId());
        BankCard bankCard=bankRepository.findByNumber(hotel.getBank());
        if (bankCard==null)
            return null;
        bankCard.setBalance(bankCard.getBalance()+checkin.getPrice());
        bankRepository.saveAndFlush(bankCard);
        checkin.setPayed(1);
        checkinRepository.saveAndFlush(checkin);
        return transferService.transferHotel(hotel);
    }

    @Override
    public ArrayList<ArrayList> getHotelStats() {
        Date today=getDayBefore(0);
        Date monthBefore=getDayBefore(30);
        ArrayList<ArrayList> list=new ArrayList<>();
        List<Hotel> hotelList=hotelRepository.findAll();

        for (Hotel hotel:hotelList){
            ArrayList<String> arr=new ArrayList<>();
            arr.add(hotel.getName()+hotel.getId());
            Integer cNum=getCheckinNum(hotel.getId(),today,monthBefore);
            if (cNum==null){
                arr.add(0+"");
            }
            else {
                arr.add(cNum+"");
            }
            list.add(arr);
        }
        return list;
    }

    @Override
    public ArrayList<ArrayList> getEarningEachDay() {
        ArrayList<ArrayList> list=new ArrayList<>();

        for (int i=14;i>0;i--){
            ArrayList<Long> arr=new ArrayList<>();
            Date date=getDayBefore(i);
            Date date1=getDayBefore(i+1);
            Integer earning=checkinRepository.getEarningByDay(date,date1);
            earning=earning==null?0:earning;
            arr.add(date.getTime());
            arr.add(Long.valueOf(earning));
            list.add(arr);
        }
        return list;
    }

    @Override
    public ArrayList<ArrayList> getEarningEachDayByHotel(int hotelid) {
        ArrayList<ArrayList> list=new ArrayList<>();

        for (int i=14;i>0;i--){
            ArrayList<Long> arr=new ArrayList<>();
            Date date=getDayBefore(i);
            Date date1=getDayBefore(i+1);
            Integer earning=checkinRepository.getEarningByDayByHotel(date,date1,hotelid);
            earning=earning==null?0:earning;
            arr.add(date.getTime());
            arr.add(Long.valueOf(earning));
            list.add(arr);
        }
        return list;
    }

    private Integer getCheckinNum(int id,Date today, Date monthBefore) {
        return checkinRepository.getCheckinNumByTime(id,today,monthBefore);
    }

    private Date getDayBefore(int i) {
        Date date=new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setDate(date.getDate()-i);
        return date;
    }

    private RoomAsign creatNewRoomAssign(NewCheckinVO vo,Room spareRoom,int id) {
        Checkin checkin=checkinRepository.findOne(id);
        RoomAsign roomAsign=new RoomAsign();
//        空余的房子
        roomAsign.setRoomByRoomId(spareRoom);
        roomAsign.setInTime(getDate(vo.getInTime()));
        roomAsign.setOutTime(getDate(vo.getOutTime()));
        roomAsign.setBookingByBookId(null);
        roomAsign.setCheckinByCheckinId(checkin);
//        1表示入住
        roomAsign.setState(1);
        return roomAsign;
    }

    private Checkin creatNewCheckin(NewCheckinVO vo) {
        Checkin checkin=new Checkin();
        checkin.setIdcard1(vo.getIdcard1());
        checkin.setIdcard2(vo.getIdcard2());
        checkin.setUser1(vo.getUser1());
        checkin.setUser2(vo.getUser2());
        checkin.setInTime(getDate(vo.getInTime()));
        checkin.setOutTime(getDate(vo.getOutTime()));
        checkin.setPrice(vo.getPrice());

        return checkin;
    }


    private Checkin creatCheckin(Booking booking) {
        Checkin checkin=new Checkin();
        checkin.setPrice(booking.getPrice());
        checkin.setBookingByBookId(booking);
        checkin.setUserByUserId(booking.getUserByUserId());
        checkin.setRoomTypeByRoomTypeId(booking.getRoomTypeByRoomTypeId());
        checkin.setInTime(booking.getInTime());
        checkin.setOutTime(booking.getOutTime());
        return checkin;
    }

    private RoomAsign creatRoomAssign(RoomType roomType, Booking booking, Room spareRoom) {
        RoomAsign roomAsign=new RoomAsign();
//        空余的房子
        roomAsign.setRoomByRoomId(spareRoom);
        roomAsign.setInTime(booking.getInTime());
        roomAsign.setOutTime(booking.getOutTime());
        roomAsign.setBookingByBookId(booking);
        roomAsign.setCheckinByCheckinId(null);
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
        List<Hotel> hotelList=hotelRepository.findAllApproved();
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
