package com.istad.banking.feature.auth;

import com.istad.banking.feature.auth.dto.AuthResponse;
import com.istad.banking.feature.auth.dto.LoginRequest;

public interface AuthService {
    AuthResponse authorities(LoginRequest loginRequest);
}
