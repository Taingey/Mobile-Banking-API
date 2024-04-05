package com.istad.banking.feature.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserResponse(
        @NotNull
        String uuid,
        @NotNull
        String name,
        String profileImage,
        String gender,
        LocalDate dob
) {
}
