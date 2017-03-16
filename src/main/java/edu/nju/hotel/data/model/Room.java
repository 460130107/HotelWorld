package edu.nju.hotel.data.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zhouxiaofan on 2017/3/15.
 */
@Entity
@Table(name = "room")
public class Room {
    private int id;
    private String name;
    private RoomType roomTypeByRoomTypeId;
    private Collection<RoomAsign> roomAsignsById;

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

        Room room = (Room) o;

        if (id != room.id) return false;
        if (name != null ? !name.equals(room.name) : room.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "roomTypeId", referencedColumnName = "id", nullable = false)
    public RoomType getRoomTypeByRoomTypeId() {
        return roomTypeByRoomTypeId;
    }

    public void setRoomTypeByRoomTypeId(RoomType roomTypeByRoomTypeId) {
        this.roomTypeByRoomTypeId = roomTypeByRoomTypeId;
    }

    @OneToMany(mappedBy = "roomByRoomId")
    public Collection<RoomAsign> getRoomAsignsById() {
        return roomAsignsById;
    }

    public void setRoomAsignsById(Collection<RoomAsign> roomAsignsById) {
        this.roomAsignsById = roomAsignsById;
    }
}
