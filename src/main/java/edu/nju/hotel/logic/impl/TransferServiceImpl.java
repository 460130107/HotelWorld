package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.model.*;
import edu.nju.hotel.logic.service.TransferService;
import edu.nju.hotel.logic.vo.*;
import org.springframework.stereotype.Component;

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

    @Override
    public BookingVO transferBooking(Booking booking) {
        BookingVO vo=new BookingVO();
        vo.setHotelId(booking.getHotelByHotelId().getId());
        vo.setCancled(booking.getCancled());
        vo.setCreatTime(booking.getCreatTime());
        vo.setDeposit(booking.getDeposit());
        vo.setEmail(booking.getEmail());
        vo.setInTime(transferDate(booking.getInTime()));
        vo.setOutTime(transferDate(booking.getOutTime()));
        vo.setCreatTime(booking.getCreatTime());
        vo.setCheckinId(booking.getCheckinsById().getId());
        vo.setRoomNum(booking.getRoomNum());
        vo.setPhone(booking.getPhone());
        vo.setNameinfo(booking.getNameinfo());
        vo.setPrice(booking.getPrice());
        vo.setRoomTypeId(booking.getRoomTypeByRoomTypeId().getId());
        vo.setUserId(booking.getUserByUserId().getId());
        return vo;
    }

    private String transferDate(Date date){
        String result="";
        result+=date.getYear()+1900;
        result+="-";
        int month=date.getMonth()+1;
        if(month<10){
            result+="0";
        }
        result+=month;
        result+="-";
        result+=date.getDay();
        return result;
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
        RoomVO roomVO=new RoomVO();
        roomVO.setName(room.getName());
        roomVO.setRoomType(room.getRoomTypeByRoomTypeId().getName());
        return roomVO;
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
