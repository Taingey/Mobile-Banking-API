package com.istad.banking.feature.user.dto;

public record PageRequest(
        int page,
        int limit
) {
}
