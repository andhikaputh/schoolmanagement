package com.telu.schoolmanagement.auth.controller;

import com.telu.schoolmanagement.auth.dto.AuthResponse;
import com.telu.schoolmanagement.auth.dto.LoginRequest;
import com.telu.schoolmanagement.auth.dto.RegisterRequest;
import com.telu.schoolmanagement.auth.service.AuthService;
import com.telu.schoolmanagement.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Authentication",
        description = "Endpoints for user authentication such as login and registration."
)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(
            summary = "User Login",
            description = "Login using NIP (employee ID) and password. Returns a JWT token and User Data if authentication is successful."
    )
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody @Valid LoginRequest request) {
        AuthResponse result = authService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", result));
    }

    @Operation(
            summary = "User Registration",
            description = "Register a new user account. Returns a JWT token and User Data upon successful registration."
    )
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        AuthResponse result = authService.register(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", result));
    }
}

