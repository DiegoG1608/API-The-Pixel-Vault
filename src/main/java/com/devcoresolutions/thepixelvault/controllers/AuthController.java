package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.AuthResponse;
import com.devcoresolutions.thepixelvault.dtos.LoginRequest;
import com.devcoresolutions.thepixelvault.dtos.RegisterRequest;
import com.devcoresolutions.thepixelvault.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") //http://localhost:8080/api/auth/
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register") //http://localhost:8080/api/auth/register
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
}