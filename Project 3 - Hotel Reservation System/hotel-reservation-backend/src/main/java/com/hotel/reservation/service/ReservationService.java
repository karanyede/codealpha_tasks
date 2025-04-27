package com.hotel.reservation.service;

import com.hotel.reservation.model.*;
import com.hotel.reservation.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              RoomRepository roomRepository,
                              UserRepository userRepository,
                              PaymentRepository paymentRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Reservation bookRoom(Long userId, Long roomId, LocalDate checkIn, LocalDate checkOut, double amount) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Room> roomOpt = roomRepository.findById(roomId);

        if (userOpt.isEmpty() || roomOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Room not found");
        }

        Room room = roomOpt.get();
        if (!room.isAvailable()) {
            throw new IllegalStateException("Room is not available");
        }

        // Simulate payment
        Payment payment = Payment.builder()
                .amount(amount)
                .status(PaymentStatus.COMPLETED)
                .paymentDate(java.time.LocalDateTime.now())
                .build();
        paymentRepository.save(payment);

        // Create reservation
        Reservation reservation = Reservation.builder()
                .user(userOpt.get())
                .room(room)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .payment(payment)
                .build();

        // Mark room as unavailable
        room.setAvailable(false);
        roomRepository.save(room);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsForUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Optional<Reservation> getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
}