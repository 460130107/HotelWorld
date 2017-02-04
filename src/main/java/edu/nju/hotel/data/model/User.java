package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zhouxiaofan on 2017/2/4.
 */
@Entity
@Table(name = "user")
public class User {
    private int id;
    private String psw;
    private String name;
    private String idcard;
    private String phone;
    private int state;
    private int level;
    private int points;
    private Collection<Booking> bookingsById;
    private Collection<Checkin> checkinsById;
    private BankCard bankCardByBank;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "idcard", nullable = false, length = 45)
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "points", nullable = false)
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (state != user.state) return false;
        if (level != user.level) return false;
        if (points != user.points) return false;
        if (psw != null ? !psw.equals(user.psw) : user.psw != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (idcard != null ? !idcard.equals(user.idcard) : user.idcard != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (psw != null ? psw.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + level;
        result = 31 * result + points;
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Booking> getBookingsById() {
        return bookingsById;
    }

    public void setBookingsById(Collection<Booking> bookingsById) {
        this.bookingsById = bookingsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Checkin> getCheckinsById() {
        return checkinsById;
    }

    public void setCheckinsById(Collection<Checkin> checkinsById) {
        this.checkinsById = checkinsById;
    }

    @ManyToOne
    @JoinColumn(name = "bank", referencedColumnName = "number")
    public BankCard getBankCardByBank() {
        return bankCardByBank;
    }

    public void setBankCardByBank(BankCard bankCardByBank) {
        this.bankCardByBank = bankCardByBank;
    }
}
