package com.istad.banking.feature.auth.dto;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}
