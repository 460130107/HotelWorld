package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
@Entity
@Table(name = "checkin")
public class Checkin {
    private int id;
    private int price;
    private int payType;
    private Timestamp creatTime = new Timestamp( new Date().getTime());
    private User userByUserId;
    private Booking bookingByBookId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkin checkin = (Checkin) o;

        if (id != checkin.id) return false;
        if (price != checkin.price) return false;
        if (payType != checkin.payType) return false;
        if (creatTime != null ? !creatTime.equals(checkin.creatTime) : checkin.creatTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + payType;
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
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

    @OneToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    public Booking getBookingByBookId() {
        return bookingByBookId;
    }

    public void setBookingByBookId(Booking bookingByBookId) {
        this.bookingByBookId = bookingByBookId;
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
