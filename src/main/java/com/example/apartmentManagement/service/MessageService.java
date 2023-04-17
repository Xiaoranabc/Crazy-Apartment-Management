package com.example.apartmentManagement.service;

import com.example.apartmentManagement.modal.Message;
import com.example.apartmentManagement.repository.MessageRepositoty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageService{
    private final MessageRepositoty messageRepositoty;

    public MessageService(MessageRepositoty messageRepositoty) {
        this.messageRepositoty = messageRepositoty;
    }
     public void saveMessage(Message message) {
        messageRepositoty.save(message);
     }
     public Message findById(Integer id) {
        return messageRepositoty.findById(id);
     }
     public List<Message> showAllMessage() {
        List<Message> messages = new ArrayList<>();
        for(Message message : messageRepositoty.findAll()) {
            messages.add(message);
        }
        return messages;
     }
     public void deleteById(Integer id) {
        Message message = messageRepositoty.findById(id);
        messageRepositoty.delete(message);
     }
}
