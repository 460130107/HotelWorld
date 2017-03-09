package edu.nju.hotel.logic.vo;


/**
 * Created by zhouxiaofan on 2017/3/7.
 */
public class HotelVO {
    private int id;
    private String name;
    private String city;
    private String location;
    private String description;
    private String psw;
    private Integer approved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }


}
