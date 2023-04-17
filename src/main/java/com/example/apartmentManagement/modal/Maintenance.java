package com.example.apartmentManagement.modal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="maintenance")
public class Maintenance implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;
    String name;
    String room;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    String description;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

    public Maintenance() {
    }
    public Maintenance(String name, String room, String description) {
        this.name = name;
        this.room = room;
        this.description = description;
    }
    public Integer getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
    public String getRoom() {return room;}
    public void setRoom(String room) {this.room = room;}
    public Date getDate() {return date;}
    public String getDescription() {return description;}
    public void setDescription() {this.description = description;}
}
