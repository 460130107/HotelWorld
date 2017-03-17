package edu.nju.hotel.logic.vo;

import edu.nju.hotel.util.constant.UserConstant;

import java.sql.Timestamp;
import java.util.logging.Level;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
public class UserVO {
    private int id;
    private String psw;
    private String name;
    private String idcard;
    private String phone;
    private int state;
    private int level;
    private int userLevel;
    private Timestamp creatTime;
    private int points;
    private double discount;
    private int balance;
    private String bank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setUserLevelAndDiscount();
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int setUserLevelAndDiscount(){
        int sum=UserConstant.LEVEL_TABEL.length;
        for (int i=0;i<sum;i++){
            if (this.level<UserConstant.LEVEL_TABEL[i]){
                this.userLevel=i;
                this.discount=UserConstant.DISCOUNT_TABEL[i];
                return i;
            }
        }
        this.userLevel=sum;
        this.discount=UserConstant.DISCOUNT_TABEL[sum];
        return sum;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
