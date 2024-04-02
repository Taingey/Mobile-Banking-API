package com.istad.banking.feature.dto;

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
