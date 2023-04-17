package com.example.apartmentManagement.repository;

import com.example.apartmentManagement.modal.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    public Room findByRoomNumber(int roomNumber);
}