package com.istad.banking.feature.accountType.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record AccountTypeRequest(
        @NotBlank
        String alias,
        @NotBlank
        String name,
        @NotNull
        String description
) {
}
