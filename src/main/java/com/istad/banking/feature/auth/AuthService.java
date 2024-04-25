package com.istad.banking.feature.auth;

import com.istad.banking.feature.auth.dto.AuthResponse;
import com.istad.banking.feature.auth.dto.LoginRequest;
import com.istad.banking.feature.auth.dto.RefreshTokenRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);
}
