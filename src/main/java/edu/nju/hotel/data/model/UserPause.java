package edu.nju.hotel.data.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zhouxiaofan on 2017/3/19.
 */
@Entity
public class UserPause {
    private int id;
    private Integer userid;
    private Timestamp creatTime=new Timestamp(new Date().getTime());

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

        UserPause userPause = (UserPause) o;

        if (id != userPause.id) return false;
        if (userid != null ? !userid.equals(userPause.userid) : userPause.userid != null) return false;
        if (creatTime != null ? !creatTime.equals(userPause.creatTime) : userPause.creatTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
        return result;
    }
}
