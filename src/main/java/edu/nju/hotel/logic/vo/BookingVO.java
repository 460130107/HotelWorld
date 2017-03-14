package edu.nju.hotel.logic.vo;

import edu.nju.hotel.data.model.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
public class BookingVO {

    private String inTime;
    private String outTime;
    private Integer roomNum;
    private String nameinfo;
    private String phone;
    private String email;
    private Integer price;
    private int cancled;
    private int deposit;
    private Timestamp creatTime = new Timestamp( new Date().getTime());
    private int userId;
    private int hotelId;
    private int roomTypeId;
    private String roomTypeName;
    private int checkinId;

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getNameinfo() {
        return nameinfo;
    }

    public void setNameinfo(String nameinfo) {
        this.nameinfo = nameinfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getCancled() {
        return cancled;
    }

    public void setCancled(int cancled) {
        this.cancled = cancled;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(int checkinId) {
        this.checkinId = checkinId;
    }


    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
}