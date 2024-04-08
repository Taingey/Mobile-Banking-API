package com.istad.banking.feature.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileImageRequest (
        @NotBlank(message = "Media is required")
        String mediaName
) {
}