package edu.nju.hotel.logic.vo;



public class HotelUpdateVO {
    private int id;
    private String name;
    private String city;
    private String location;
    private String description;
    private String psw;
    /**
     * 0表示未审批，1表示审批通过，2表示审批不通过
     */
    private Integer approved=0;
    private int hotelId;
    /**
     * 时间格式yyyy-MM-dd HH:mm
     */
    private String creatTime;


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
        String result="";
        for (int i=0;i<this.psw.length();i++){
            result+="*";
        }
        return result;
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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
}
