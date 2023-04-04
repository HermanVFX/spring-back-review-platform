package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserListDto;
import com.hermanvfx.springbackreviewplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserListDto findAllUser(Pageable pageable);

    UserDto findUserById(UUID userId);

    UserDto create(ShortUserDto user);

    UserDto update(UserDto user, UUID id);

    void delete(UUID id);
}
