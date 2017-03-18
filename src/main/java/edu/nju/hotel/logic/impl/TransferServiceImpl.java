package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.model.*;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.vo.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaofan on 2017/3/3.
 */
@Component
public class TransferServiceImpl implements TransferService {
    @Override
    public UserVO transferUser(User user) {

        if (user==null){
            return null;
        }
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
        if (admin==null){
            return null;
        }
        ManagerVO vo=new ManagerVO();
        vo.setId(admin.getId());
        vo.setPsw(admin.getPsw());
        vo.setName(admin.getName());
        return vo;
    }

    @Override
    public HotelVO transferHotel(Hotel hotel) {
        if (hotel==null){
            return null;
        }
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

    @Override
    public BookingVO transferBooking(Booking booking) {
        if (booking==null){
            return null;
        }
        BookingVO vo=new BookingVO();
        vo.setId(booking.getId());
        vo.setHotelId(booking.getHotelByHotelId().getId());
        vo.setCancled(booking.getCancled());
        vo.setCreatTime(booking.getCreatTime());
        vo.setDeposit(booking.getDeposit());
        vo.setEmail(booking.getEmail());
        vo.setInTime(transferDate(booking.getInTime()));
        vo.setOutTime(transferDate(booking.getOutTime()));
        vo.setCreatTime(booking.getCreatTime());
        vo.setRoomNum(booking.getRoomNum());
        vo.setRoomTypeName(booking.getRoomTypeByRoomTypeId().getName());
        vo.setPhone(booking.getPhone());
        vo.setNameinfo(booking.getNameinfo());
        vo.setPrice(booking.getPrice());
        vo.setRoomTypeId(booking.getRoomTypeByRoomTypeId().getId());
        vo.setUserId(booking.getUserByUserId().getId());
        if(booking.getCancled()>0){
            vo.setStatus(2);
            return vo;
        }
        if(booking.getChecked()>0){
            vo.setStatus(3);
            return vo;
        }
        if(booking.getInTime().before(new Date())){
            vo.setStatus(1);
        }
        return vo;
    }

    private String transferDate(Date date){

        return date.toString();
    }

    @Override
    public List<RoomVO> transferRooms(List<Room> list) {
        List<RoomVO> roomVOS=new ArrayList<RoomVO>();
        for (int i=0;i<list.size();i++){
            RoomVO vo=transferRoom(list.get(i));
            roomVOS.add(vo);
        }
        return roomVOS;
    }

    @Override
    public RoomVO transferRoom(Room room) {
        if (room==null)return null;
        RoomVO roomVO=new RoomVO();
        roomVO.setName(room.getName());
        roomVO.setRoomType(room.getRoomTypeByRoomTypeId().getName());
        return roomVO;
    }

    @Override
    public RoomAssignVO transferRoomAssign(RoomAsign roomAssign) {
        if (roomAssign==null) return null;
        RoomAssignVO vo= new RoomAssignVO();
        vo.setId(roomAssign.getId());
        vo.setRoomName(roomAssign.getRoomByRoomId().getName());
        vo.setRoomType(roomAssign.getRoomByRoomId().getRoomTypeByRoomTypeId().getName());

        return vo;
    }

    @Override
    public CheckinVO transferCheckin(Checkin checkin) {
        if (checkin==null) return null;
        CheckinVO vo=new CheckinVO();
        vo.setId(checkin.getId());
        vo.setIdcard1(checkin.getIdcard1());
        vo.setIdcard2(checkin.getIdcard2());
        vo.setUser1(checkin.getUser1());
        vo.setUser2(checkin.getUser2());
        vo.setPayType(checkin.getPayType());
        if (checkin.getBookingByBookId()==null){
            vo.setBookingId(0);
        }else {
            vo.setBookingId(checkin.getBookingByBookId().getId());
        }
        vo.setInTime(checkin.getInTime().toString());
        vo.setOutTime(checkin.getOutTime().toString());
        vo.setPrice(checkin.getPrice());
        vo.setRoomTypeId(checkin.getRoomTypeByRoomTypeId().getId());
        vo.setRoomTypeName(checkin.getRoomTypeByRoomTypeId().getName());
        return vo;
    }

    @Override
    public HotelUpdateVO transferHotelUpdate(HotelUpdate hotelUpdate) {
        HotelUpdateVO vo=new HotelUpdateVO();
        vo.setCity(hotelUpdate.getCity());
        vo.setLocation(hotelUpdate.getLocation());
        vo.setPsw(hotelUpdate.getPsw());
        vo.setHotelId(hotelUpdate.getHotelByHotelId().getId());
        vo.setDescription(hotelUpdate.getDescription());
        vo.setName(hotelUpdate.getName());
        vo.setCreatTime(transTimestampToString(hotelUpdate.getCreatTime()));
        vo.setApproved(hotelUpdate.getApproved());
        vo.setId(hotelUpdate.getId());
        return vo;

    }

    @Override
    public List<PlanVO> transferPlans(List<Plan> list) {
        List<PlanVO> planVOS=new ArrayList<PlanVO>();
        for (int i=0;i<list.size();i++){
            PlanVO vo=transferPlan(list.get(i));
            planVOS.add(vo);
        }
        return planVOS;
    }

    @Override
    public List<RoomTypeVO> transferRoomTypes(List<RoomType> list) {
        List<RoomTypeVO> roomTypeVOS=new ArrayList<RoomTypeVO>();
        for (RoomType roomType:list){
            RoomTypeVO vo=transferRoomType(roomType);
            roomTypeVOS.add(vo);
        }
        return roomTypeVOS;
    }

    @Override
    public List<HotelVO> transferHotels(List<Hotel> hotelList) {
        List<HotelVO> hotelVOS=new ArrayList<HotelVO>();
        for (Hotel hotel:hotelList){
            HotelVO vo=transferHotel(hotel);
            hotelVOS.add(vo);
        }
        return hotelVOS;
    }

    @Override
    public List<BookingVO> transferBookings(List<Booking> bookingList) {
        List<BookingVO> bookingVOS=new ArrayList<>();
        for (Booking booking:bookingList){
            BookingVO vo=transferBooking(booking);
            bookingVOS.add(vo);
        }
        return bookingVOS;
    }

    @Override
    public List<RoomAssignVO> transferRoomAssigns(List<RoomAsign> roomAsigns) {
        List<RoomAssignVO> roomAssignVOS=new ArrayList<>();
        for (RoomAsign roomAsign:roomAsigns){
            RoomAssignVO roomAssignVO=transferRoomAssign(roomAsign);
            roomAssignVOS.add(roomAssignVO);
        }
        return roomAssignVOS;
    }

    @Override
    public List<CheckinVO> transferCheckins(List<Checkin> checkinList) {
        List<CheckinVO> list=new ArrayList<>();
        for (Checkin checkin:checkinList){
            CheckinVO vo=transferCheckin(checkin);
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<HotelUpdateVO> transferHotelUpdates(List<HotelUpdate> hotelUpdateList) {
        List<HotelUpdateVO> list=new ArrayList<>();
        for (HotelUpdate update:hotelUpdateList ){
            HotelUpdateVO vo=transferHotelUpdate(update);
            list.add(vo);
        }
        return list;
    }

    @Override
    public String transTimestampToString(Timestamp t) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(t);

    }

    private RoomTypeVO transferRoomType(RoomType roomType) {
        RoomTypeVO vo=new RoomTypeVO();
        vo.setRoomType(roomType.getName());
        vo.setHotelId(roomType.getHotelByHotelId().getId());
        vo.setId(roomType.getId());
        return vo;
    }


    public PlanVO transferPlan(Plan plan) {
        PlanVO vo=new PlanVO();
        vo.setRoomType(plan.getRoomTypeByRoomTypeId().getName());
        vo.setId(plan.getId());
        vo.setEndTime(plan.getEndTime());
        vo.setStartTime(plan.getStartTime());
        vo.setHotelName(plan.getHotelByHotelId().getName());
        vo.setPrice(plan.getPrice());
        return vo;
    }
}
