package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zhouxiaofan on 2017/3/15.
 */
@Entity
@Table(name = "roomType")
public class RoomType {
    private int id;
    private String name;
    private Collection<Booking> bookingsById;
    private Collection<Checkin> checkinsById;
    private Collection<Plan> plansById;
    private Collection<Room> roomsById;
    private Hotel hotelByHotelId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomType roomType = (RoomType) o;

        if (id != roomType.id) return false;
        if (name != null ? !name.equals(roomType.name) : roomType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roomTypeByRoomTypeId")
    public Collection<Booking> getBookingsById() {
        return bookingsById;
    }

    public void setBookingsById(Collection<Booking> bookingsById) {
        this.bookingsById = bookingsById;
    }

    @OneToMany(mappedBy = "roomTypeByRoomTypeId")
    public Collection<Checkin> getCheckinsById() {
        return checkinsById;
    }

    public void setCheckinsById(Collection<Checkin> checkinsById) {
        this.checkinsById = checkinsById;
    }

    @OneToMany(mappedBy = "roomTypeByRoomTypeId")
    public Collection<Plan> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<Plan> plansById) {
        this.plansById = plansById;
    }

    @OneToMany(mappedBy = "roomTypeByRoomTypeId")
    public Collection<Room> getRoomsById() {
        return roomsById;
    }

    public void setRoomsById(Collection<Room> roomsById) {
        this.roomsById = roomsById;
    }

    @ManyToOne
    @JoinColumn(name = "hotelId", referencedColumnName = "id")
    public Hotel getHotelByHotelId() {
        return hotelByHotelId;
    }

    public void setHotelByHotelId(Hotel hotelByHotelId) {
        this.hotelByHotelId = hotelByHotelId;
    }
}
