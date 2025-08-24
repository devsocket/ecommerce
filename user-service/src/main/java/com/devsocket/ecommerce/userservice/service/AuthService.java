// service/AuthService.java
package com.devsocket.ecommerce.userservice.service;

import com.devsocket.ecommerce.userservice.dto.AuthRequest;
import com.devsocket.ecommerce.userservice.dto.AuthResponse;
import com.devsocket.ecommerce.userservice.dto.RegisterRequest;
import com.devsocket.ecommerce.userservice.entity.User;
import com.devsocket.ecommerce.userservice.repository.UserRepository;
import com.devsocket.ecommerce.userservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for handling authentication and registration logic.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(
                request.username(), request.password()
        );
        authManager.authenticate(authToken);

        var token = jwtUtil.generateToken(request.username());
        return new AuthResponse(token);
    }
}
