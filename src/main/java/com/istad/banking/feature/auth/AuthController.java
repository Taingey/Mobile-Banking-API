package com.istad.banking.feature.auth;

import com.istad.banking.feature.auth.dto.AuthResponse;
import com.istad.banking.feature.auth.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/login")
public class AuthController {
    private final AuthService authService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse authResponse(@Valid @RequestBody LoginRequest loginRequest){
        return authService.authorities(loginRequest);
    }
}
