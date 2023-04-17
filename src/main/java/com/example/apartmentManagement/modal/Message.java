package com.example.apartmentManagement.modal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="message")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String name;
    String subject;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    @ElementCollection
    @CollectionTable(name = "details", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "detail")
    List<String> details;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    User user;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

    public Message() {

    }
       public Message(String name, String subject, List<String> details, User user) {
        this.name = name;
        this.subject = subject;
        this.details = details;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject() {
        this.subject = subject;
    }
    public Date getDate() {return date;}

    public List<String> getDetails() {
        for(String detail : details) {
            System.out.println(detail);
        }
        return details;}

    public void setDetails(List<String> details) {this.details = details;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
