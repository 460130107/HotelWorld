package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zhouxiaofan on 2017/3/18.
 */
@Entity
public class Hotel {
    private int id;
    private String name;
    private String city;
    private String location;
    private String description;
    private String psw;
    private int approved=0;
    private String bank;
    private Collection<Booking> bookingsById;
    private Collection<HotelUpdate> hotelUpdatesById;
    private Collection<Plan> plansById;
    private Collection<RoomType> roomTypesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bank", nullable = true, length = 20)
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 200)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "psw", nullable = false, length = 11)
    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Basic
    @Column(name = "approved", nullable = true)
    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != hotel.id) return false;
        if (approved != hotel.approved) return false;
        if (bank != null ? !bank.equals(hotel.bank) : hotel.bank != null) return false;
        if (name != null ? !name.equals(hotel.name) : hotel.name != null) return false;
        if (city != null ? !city.equals(hotel.city) : hotel.city != null) return false;
        if (location != null ? !location.equals(hotel.location) : hotel.location != null) return false;
        if (description != null ? !description.equals(hotel.description) : hotel.description != null) return false;
        if (psw != null ? !psw.equals(hotel.psw) : hotel.psw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + approved;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (psw != null ? psw.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "hotelByHotelId")
    public Collection<Booking> getBookingsById() {
        return bookingsById;
    }

    public void setBookingsById(Collection<Booking> bookingsById) {
        this.bookingsById = bookingsById;
    }

    @OneToMany(mappedBy = "hotelByHotelId")
    public Collection<HotelUpdate> getHotelUpdatesById() {
        return hotelUpdatesById;
    }

    public void setHotelUpdatesById(Collection<HotelUpdate> hotelUpdatesById) {
        this.hotelUpdatesById = hotelUpdatesById;
    }

    @OneToMany(mappedBy = "hotelByHotelId")
    public Collection<Plan> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<Plan> plansById) {
        this.plansById = plansById;
    }

    @OneToMany(mappedBy = "hotelByHotelId")
    public Collection<RoomType> getRoomTypesById() {
        return roomTypesById;
    }

    public void setRoomTypesById(Collection<RoomType> roomTypesById) {
        this.roomTypesById = roomTypesById;
    }
}
