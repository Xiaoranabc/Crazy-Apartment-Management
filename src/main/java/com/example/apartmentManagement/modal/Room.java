package com.example.apartmentManagement.modal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="myroom")
public class Room implements Serializable{
    @Id
    @Column(name = "roomNumber")
    int roomNumber;
    int price;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL, orphanRemoval = true)
    List<User> usersInRoom = new ArrayList<>();
    String parking;
//    @Lob
//    @Column(columnDefinition = "BLOB")
//    private byte[] image;

    public Room() {

    }
    public Room(int RoomNumber, int price) {
        this.roomNumber = RoomNumber;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public List<User> getUsersInRoom() {
        System.out.println(usersInRoom.size());
        return usersInRoom;
    }
    public void setUsersInRoom(User user) {
        usersInRoom.add(user);
    }

    public String getParking() {
        if(parking==null) {
            parking = "";
        }
        return parking;
    }
    public void setParking(String parking) {
        this.parking = parking;
    }

//    public String getAllUsers() {
//        for(User user : usersInRoom) {
//            if(usersInRoom.size()==0) {
//                return "none";
//            } else {
//                String userInfo = user.getId().toString()+" "+user.getName();
//            }
//        }
//        return allUsers;
//    }
}
