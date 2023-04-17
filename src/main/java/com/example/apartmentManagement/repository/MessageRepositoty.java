package com.example.apartmentManagement.repository;

import com.example.apartmentManagement.modal.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepositoty extends CrudRepository<Message, Integer>{
        public Message findById(int id);

}
