package com.istad.banking.feature.auth;

import com.istad.banking.feature.auth.dto.AuthResponse;
import com.istad.banking.feature.auth.dto.ChangePasswordRequest;
import com.istad.banking.feature.auth.dto.LoginRequest;
import com.istad.banking.feature.auth.dto.RefreshTokenRequest;
import jakarta.validation.Valid;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);

    void changePassword(Jwt jwt, @Valid ChangePasswordRequest changePassword);
}
