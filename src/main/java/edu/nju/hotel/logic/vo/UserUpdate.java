package edu.nju.hotel.logic.vo;

import java.sql.Timestamp;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
public class UserUpdate {

    private String idcard;
    private String bank;

    private String phone;
    private String psw;
    private int id;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
