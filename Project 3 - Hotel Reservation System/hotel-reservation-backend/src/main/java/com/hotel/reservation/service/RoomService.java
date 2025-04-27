package com.hotel.reservation.service;

import com.hotel.reservation.model.Room;
import com.hotel.reservation.model.RoomCategory;
import com.hotel.reservation.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableTrue();
    }

    public List<Room> getAvailableRoomsByCategory(RoomCategory category) {
        return roomRepository.findByCategoryAndAvailableTrue(category);
    }
}