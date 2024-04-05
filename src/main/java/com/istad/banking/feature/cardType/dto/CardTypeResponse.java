package com.istad.banking.feature.cardType.dto;

import jakarta.validation.constraints.NotNull;

public record CardTypeResponse(
        @NotNull
        String name,
        Boolean isDeleted
) {
}
