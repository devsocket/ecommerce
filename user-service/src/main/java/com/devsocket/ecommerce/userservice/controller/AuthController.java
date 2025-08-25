// controller/AuthController.java
package com.devsocket.ecommerce.userservice.controller;

import com.devsocket.ecommerce.userservice.dto.AuthRequest;
import com.devsocket.ecommerce.userservice.dto.AuthResponse;
import com.devsocket.ecommerce.userservice.dto.RegisterRequest;
import com.devsocket.ecommerce.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
