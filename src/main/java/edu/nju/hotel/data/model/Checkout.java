package edu.nju.hotel.data.model;

import javax.persistence.*;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
@Entity
@Table(name = "checkout")
public class Checkout {
    private int id;
    private String creatTime;
    private int roomAsignId;
    private Checkin checkinByCheckinId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "creatTime", nullable = true)
    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Basic
    @Column(name = "roomAsignId", nullable = false)
    public int getRoomAsignId() {
        return roomAsignId;
    }

    public void setRoomAsignId(int roomAsignId) {
        this.roomAsignId = roomAsignId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkout checkout = (Checkout) o;

        if (id != checkout.id) return false;
        if (roomAsignId != checkout.roomAsignId) return false;
        if (creatTime != null ? !creatTime.equals(checkout.creatTime) : checkout.creatTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
        result = 31 * result + roomAsignId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "checkinId", referencedColumnName = "id", nullable = false)
    public Checkin getCheckinByCheckinId() {
        return checkinByCheckinId;
    }

    public void setCheckinByCheckinId(Checkin checkinByCheckinId) {
        this.checkinByCheckinId = checkinByCheckinId;
    }
}
