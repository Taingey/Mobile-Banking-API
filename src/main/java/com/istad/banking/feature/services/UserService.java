package com.istad.banking.feature.services;

import com.istad.banking.base.BasedMessage;
import com.istad.banking.feature.dto.*;
import org.springframework.data.domain.Page;

public interface UserService {
    void createNew(UserCreateRequest userCreateRequest);

    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    UserResponse findByUuid(String uuid);

    BasedMessage blockByUuid(String uuid);

    void deletedUserByUuid(String uuid);

    UserResponse findDetailsByUuid(String uuid);

    Page<UserResponse> findList(int page, int limit);

    void changePassword(String phoneNumber, String oldPassword, String newPassword, String confirmedPassword);

    UserResponse updateProfile(String uuid, UserProfileUpdateRequest request);
}
