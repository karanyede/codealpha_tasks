package com.hotel.reservation;

import com.hotel.reservation.model.*;
import com.hotel.reservation.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public DataInitializer(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== DataInitializer START ===");
        try {
            roomRepository.save(Room.builder()
                    .number("101")
                    .category(RoomCategory.SINGLE)
                    .price(1000)
                    .available(true)
                    .build());
            roomRepository.save(Room.builder()
                    .number("102")
                    .category(RoomCategory.DOUBLE)
                    .price(1500)
                    .available(true)
                    .build());
            roomRepository.save(Room.builder()
                    .number("201")
                    .category(RoomCategory.SUITE)
                    .price(2500)
                    .available(true)
                    .build());
            userRepository.save(User.builder()
                    .name("Alice")
                    .email("alice@example.com")
                    .build());
            userRepository.save(User.builder()
                    .name("Bob")
                    .email("bob@example.com")
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while initializing sample data: " + e.getMessage());
        }
        System.out.println("=== DataInitializer END ===");
    }
}