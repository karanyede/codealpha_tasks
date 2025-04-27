package com.hotel.reservation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"") // <-- This is the fix!
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}