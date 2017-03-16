package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by zhouxiaofan on 2017/3/15.
 */
@Entity
@Table(name = "checkin")
public class Checkin {
    private int id;
    private int price;
    private int payType;
    private Timestamp creatTime=new Timestamp(new Date().getTime());
    private Date inTime;
    private Date outTime;
    private String user1;
    private String user2;
    private String idcard1;
    private String idcard2;
    private User userByUserId;
    private Booking bookingByBookId;
    private RoomType roomTypeByRoomTypeId;
    private Collection<Checkout> checkoutsById;
    private Collection<RoomAsign> roomAsignsById;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "payType", nullable = false)
    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "creatTime", nullable = true)
    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    @Basic
    @Column(name = "inTime", nullable = true)
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Basic
    @Column(name = "outTime", nullable = true)
    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @Basic
    @Column(name = "user1", nullable = true, length = 20)
    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    @Basic
    @Column(name = "user2", nullable = true, length = 20)
    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Basic
    @Column(name = "idcard1", nullable = true, length = 20)
    public String getIdcard1() {
        return idcard1;
    }

    public void setIdcard1(String idcard1) {
        this.idcard1 = idcard1;
    }

    @Basic
    @Column(name = "idcard2", nullable = true, length = 20)
    public String getIdcard2() {
        return idcard2;
    }

    public void setIdcard2(String idcard2) {
        this.idcard2 = idcard2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkin checkin = (Checkin) o;

        if (id != checkin.id) return false;
        if (price != checkin.price) return false;
        if (payType != checkin.payType) return false;
        if (creatTime != null ? !creatTime.equals(checkin.creatTime) : checkin.creatTime != null) return false;
        if (inTime != null ? !inTime.equals(checkin.inTime) : checkin.inTime != null) return false;
        if (outTime != null ? !outTime.equals(checkin.outTime) : checkin.outTime != null) return false;
        if (user1 != null ? !user1.equals(checkin.user1) : checkin.user1 != null) return false;
        if (user2 != null ? !user2.equals(checkin.user2) : checkin.user2 != null) return false;
        if (idcard1 != null ? !idcard1.equals(checkin.idcard1) : checkin.idcard1 != null) return false;
        if (idcard2 != null ? !idcard2.equals(checkin.idcard2) : checkin.idcard2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + payType;
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
        result = 31 * result + (inTime != null ? inTime.hashCode() : 0);
        result = 31 * result + (outTime != null ? outTime.hashCode() : 0);
        result = 31 * result + (user1 != null ? user1.hashCode() : 0);
        result = 31 * result + (user2 != null ? user2.hashCode() : 0);
        result = 31 * result + (idcard1 != null ? idcard1.hashCode() : 0);
        result = 31 * result + (idcard2 != null ? idcard2.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    public Booking getBookingByBookId() {
        return bookingByBookId;
    }

    public void setBookingByBookId(Booking bookingByBookId) {
        this.bookingByBookId = bookingByBookId;
    }

    @ManyToOne
    @JoinColumn(name = "roomTypeId", referencedColumnName = "id")
    public RoomType getRoomTypeByRoomTypeId() {
        return roomTypeByRoomTypeId;
    }

    public void setRoomTypeByRoomTypeId(RoomType roomTypeByRoomTypeId) {
        this.roomTypeByRoomTypeId = roomTypeByRoomTypeId;
    }

    @OneToMany(mappedBy = "checkinByCheckinId")
    public Collection<Checkout> getCheckoutsById() {
        return checkoutsById;
    }

    public void setCheckoutsById(Collection<Checkout> checkoutsById) {
        this.checkoutsById = checkoutsById;
    }

    @OneToMany(mappedBy = "checkinByCheckinId")
    public Collection<RoomAsign> getRoomAsignsById() {
        return roomAsignsById;
    }

    public void setRoomAsignsById(Collection<RoomAsign> roomAsignsById) {
        this.roomAsignsById = roomAsignsById;
    }
}
