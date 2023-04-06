package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserService {

    UserListDto findAllUser(Pageable pageable);

    UserDto findUserById(UUID userId);

    UserDto create(ShortUserDto user);

    UserDto update(UserDto user, UUID id);

    void delete(UUID id);

    UserDto findUserByEmail(String email);
}
