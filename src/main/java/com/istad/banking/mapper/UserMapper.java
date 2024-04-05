package com.istad.banking.mapper;

import com.istad.banking.domain.User;
import com.istad.banking.feature.user.dto.UserCreateRequest;
import com.istad.banking.feature.user.dto.UserProfileUpdateRequest;
import com.istad.banking.feature.user.dto.UserResponse;
import com.istad.banking.feature.user.dto.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromUserCreateRequest(UserCreateRequest userCreateRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserCreateRequest(UserUpdateRequest request,@MappingTarget User user);
    UserResponse toUserResponse(User user);
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    @Mapping(target = "password", source = "newPassword")
    void updatePassword(String newPassword, @MappingTarget User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateUserFromProfileUpdateRequest(UserProfileUpdateRequest userProfileUpdateRequest, @MappingTarget User user);
}