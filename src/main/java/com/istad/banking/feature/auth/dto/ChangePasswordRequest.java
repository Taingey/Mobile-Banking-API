package com.istad.banking.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank(message = "OldPassword is required")
        String oldPassword,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "confirmedPassword is required")
        String confirmedPassword
) {
}
