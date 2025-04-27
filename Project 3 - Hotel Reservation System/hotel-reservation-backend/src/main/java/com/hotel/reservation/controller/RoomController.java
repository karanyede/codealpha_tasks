package com.hotel.reservation.controller;

import com.hotel.reservation.model.Room;
import com.hotel.reservation.model.RoomCategory;
import com.hotel.reservation.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    @GetMapping("/available/{category}")
    public List<Room> getAvailableRoomsByCategory(@PathVariable RoomCategory category) {
        return roomService.getAvailableRoomsByCategory(category);
    }
}