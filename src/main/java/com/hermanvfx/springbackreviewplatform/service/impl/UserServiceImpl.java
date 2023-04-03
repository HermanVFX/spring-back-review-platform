package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import com.hermanvfx.springbackreviewplatform.entity.User;
import com.hermanvfx.springbackreviewplatform.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return null;
    }

    @Override
    public UserDto findUserById(UUID userId) {
        return null;
    }

    @Override
    public UserDto create(ShortUserDto user) {
        return null;
    }

    @Override
    public UserDto update(UserDto user, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
