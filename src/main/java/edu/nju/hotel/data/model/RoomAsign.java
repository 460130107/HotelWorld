package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
@Entity
@Table(name = "roomAsign")
public class RoomAsign {
    private int id;
    private String user1;
    private String idcard1;
    private String user2;
    private String idcard2;
    private Date inTime;
    private Date outTime;
    private int state;
    private Room roomByRoomId;
    private Checkin checkinByCheckinId;
    private Booking bookingByBookId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user1", nullable = false, length = 45)
    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    @Basic
    @Column(name = "idcard1", nullable = false, length = 45)
    public String getIdcard1() {
        return idcard1;
    }

    public void setIdcard1(String idcard1) {
        this.idcard1 = idcard1;
    }

    @Basic
    @Column(name = "user2", nullable = true, length = 45)
    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Basic
    @Column(name = "idcard2", nullable = true, length = 45)
    public String getIdcard2() {
        return idcard2;
    }

    public void setIdcard2(String idcard2) {
        this.idcard2 = idcard2;
    }

    @Basic
    @Column(name = "inTime", nullable = false)
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Basic
    @Column(name = "outTime", nullable = false)
    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomAsign roomAsign = (RoomAsign) o;

        if (id != roomAsign.id) return false;
        if (state != roomAsign.state) return false;
        if (user1 != null ? !user1.equals(roomAsign.user1) : roomAsign.user1 != null) return false;
        if (idcard1 != null ? !idcard1.equals(roomAsign.idcard1) : roomAsign.idcard1 != null) return false;
        if (user2 != null ? !user2.equals(roomAsign.user2) : roomAsign.user2 != null) return false;
        if (idcard2 != null ? !idcard2.equals(roomAsign.idcard2) : roomAsign.idcard2 != null) return false;
        if (inTime != null ? !inTime.equals(roomAsign.inTime) : roomAsign.inTime != null) return false;
        if (outTime != null ? !outTime.equals(roomAsign.outTime) : roomAsign.outTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user1 != null ? user1.hashCode() : 0);
        result = 31 * result + (idcard1 != null ? idcard1.hashCode() : 0);
        result = 31 * result + (user2 != null ? user2.hashCode() : 0);
        result = 31 * result + (idcard2 != null ? idcard2.hashCode() : 0);
        result = 31 * result + (inTime != null ? inTime.hashCode() : 0);
        result = 31 * result + (outTime != null ? outTime.hashCode() : 0);
        result = 31 * result + state;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    public Room getRoomByRoomId() {
        return roomByRoomId;
    }

    public void setRoomByRoomId(Room roomByRoomId) {
        this.roomByRoomId = roomByRoomId;
    }

    @ManyToOne
    @JoinColumn(name = "checkinId", referencedColumnName = "id")
    public Checkin getCheckinByCheckinId() {
        return checkinByCheckinId;
    }

    public void setCheckinByCheckinId(Checkin checkinByCheckinId) {
        this.checkinByCheckinId = checkinByCheckinId;
    }

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    public Booking getBookingByBookId() {
        return bookingByBookId;
    }

    public void setBookingByBookId(Booking bookingByBookId) {
        this.bookingByBookId = bookingByBookId;
    }
}
