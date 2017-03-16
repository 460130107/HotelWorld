package edu.nju.hotel.logic.vo;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
public class CheckinListVO {
    private List<NewCheckinVO> checkinList;
    private int payType;
    private int userId;
    private int price;

    public List<NewCheckinVO> getCheckinList() {
        return checkinList;
    }

    public void setCheckinList(List<NewCheckinVO> checkinList) {
        this.checkinList = checkinList;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}