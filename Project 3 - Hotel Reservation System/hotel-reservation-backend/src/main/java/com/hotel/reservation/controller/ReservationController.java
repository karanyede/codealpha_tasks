package com.hotel.reservation.controller;

import com.hotel.reservation.model.Reservation;
import com.hotel.reservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getReservationsForUser(@PathVariable Long userId) {
        return reservationService.getReservationsForUser(userId);
    }

    @GetMapping("/{reservationId}")
    public Optional<Reservation> getReservation(@PathVariable Long reservationId) {
        return reservationService.getReservation(reservationId);
    }

    @PostMapping("/book")
    public Reservation bookRoom(@RequestParam Long userId,
                                @RequestParam Long roomId,
                                @RequestParam String checkIn,
                                @RequestParam String checkOut,
                                @RequestParam double amount) {
        System.out.println("Booking request: userId=" + userId + ", roomId=" + roomId + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", amount=" + amount);
        LocalDate checkInDate = LocalDate.parse(checkIn);
        LocalDate checkOutDate = LocalDate.parse(checkOut);
        return reservationService.bookRoom(userId, roomId, checkInDate, checkOutDate, amount);
    }
}