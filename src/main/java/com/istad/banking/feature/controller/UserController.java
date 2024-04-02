package com.istad.banking.feature.controller;


import com.istad.banking.base.BasedMessage;
import com.istad.banking.feature.dto.UserCreateRequest;
import com.istad.banking.feature.dto.UserProfileUpdateRequest;
import com.istad.banking.feature.dto.UserResponse;
import com.istad.banking.feature.dto.UserUpdateRequest;
import com.istad.banking.feature.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<UserResponse> findUser(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "2") int limit

    ){
        return userService.findList(page, limit);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        userService.createNew(userCreateRequest);
    }

    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }

    @GetMapping("/{uuid}")
    UserResponse findByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    void deletedUserByUuid(@PathVariable String uuid){
        userService.deletedUserByUuid(uuid);
    }

    @PutMapping("/changePassword")
    public void changePassword(
            @RequestParam String phoneNumber,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmedPassword
    ){
        userService.changePassword(phoneNumber, oldPassword, newPassword,confirmedPassword);
    }

    @PatchMapping("/{uuid}/updateProfile")
    public UserResponse updateProfile(@PathVariable String uuid,
                              @RequestBody UserProfileUpdateRequest userProfileUpdateRequest){
         return userService.updateProfile(uuid, userProfileUpdateRequest);
    }
}