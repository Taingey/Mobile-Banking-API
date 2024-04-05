package com.istad.banking.feature.user.dto;

import java.util.List;

public record UserDetailsResponse(
        String name,
        String gender,
        String dob,
        String nationalCard,
        String studentIdCard,
        List<RoleNameResponse> roles
) {
}
