package com.hotel.reservation.repository;

import com.hotel.reservation.model.Room;
import com.hotel.reservation.model.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailableTrue();
    List<Room> findByCategoryAndAvailableTrue(RoomCategory category);
}