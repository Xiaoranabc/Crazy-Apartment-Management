package com.example.apartmentManagement.modal;

import sun.nio.cs.MS1250;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="mytable")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String name;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "room_number")
    Room room;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Message> messages = new ArrayList<>();

    public User() {
    }

    public User(String role, String name, String email, String password) {
        super();
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Integer getId() {return id;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Room room() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public List<Message> getMessages() {return messages;}
    public void setMessages(List<Message> messages) {this.messages = messages;}

    @Override
    public String toString() {
        return "";
    }





}