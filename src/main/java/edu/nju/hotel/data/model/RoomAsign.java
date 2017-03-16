package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhouxiaofan on 2017/3/15.
 */
@Entity
@Table(name = "roomAsign")
public class RoomAsign {
    private int id;
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
        if (inTime != null ? !inTime.equals(roomAsign.inTime) : roomAsign.inTime != null) return false;
        if (outTime != null ? !outTime.equals(roomAsign.outTime) : roomAsign.outTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
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
