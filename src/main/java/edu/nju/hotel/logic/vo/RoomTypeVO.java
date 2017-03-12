package edu.nju.hotel.logic.vo;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
public class RoomTypeVO {
    private int id;

    private int hotelId;

    private String roomType;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}