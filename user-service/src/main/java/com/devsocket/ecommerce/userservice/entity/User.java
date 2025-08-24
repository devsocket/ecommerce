package com.devsocket.ecommerce.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique username for login.
     */
    @Column(unique = true, nullable = false)
    private String username;
    /**
     * Hashed password for authentication.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Role of the user (e.g., USER, ADMIN).
     */
    private String role;
}
