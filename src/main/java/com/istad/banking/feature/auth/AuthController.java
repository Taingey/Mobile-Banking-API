package com.istad.banking.feature.auth;

import com.istad.banking.feature.auth.dto.AuthResponse;
import com.istad.banking.feature.auth.dto.ChangePasswordRequest;
import com.istad.banking.feature.auth.dto.LoginRequest;
import com.istad.banking.feature.auth.dto.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh")
    AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refresh(refreshTokenRequest);
    }
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PutMapping("/change-password")
    void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
                        @AuthenticationPrincipal Jwt jwt){
        authService.changePassword(jwt, changePasswordRequest);
    }
}
