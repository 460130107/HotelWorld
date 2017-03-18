package edu.nju.hotel.logic.vo;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
public class HotelBillVO {

    private int hotelId;
    private int money;
    private String hotelName;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
