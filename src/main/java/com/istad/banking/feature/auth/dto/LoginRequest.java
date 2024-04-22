package com.istad.banking.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Phone Number is no require")
        String phoneNumber,

        @NotBlank(message = "password is no require")
        String password
) {
}
