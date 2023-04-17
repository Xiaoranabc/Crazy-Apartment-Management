package com.example.apartmentManagement.service;

import com.example.apartmentManagement.modal.Room;
import com.example.apartmentManagement.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }
    public Room findByNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    public List<Room> showAllRooms() {
        List<Room> rooms = new ArrayList<>();
        for(Room room :roomRepository.findAll()) {
            if(room!=null) {
                rooms.add(room);
            }
        }
        return rooms;
    }

}
