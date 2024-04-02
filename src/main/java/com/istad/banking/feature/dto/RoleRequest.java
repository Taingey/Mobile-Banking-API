package com.istad.banking.feature.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank
        String name
) {
}
