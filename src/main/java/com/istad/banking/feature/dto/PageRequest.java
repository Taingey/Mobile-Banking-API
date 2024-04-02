package com.istad.banking.feature.dto;

public record PageRequest(
        int page,
        int limit
) {
}
