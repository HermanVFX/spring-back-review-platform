package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.UserApi;
import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import org.springframework.http.ResponseEntity;

public class UserController implements UserApi {
    @Override
    public ResponseEntity<UserDto> createUser(ShortUserDto shortUserDto) {
        return null;
    }
}
