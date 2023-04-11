package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserListDto;
import com.hermanvfx.springbackreviewplatform.entity.User;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.UserMapper;
import com.hermanvfx.springbackreviewplatform.repository.UserRepository;
import com.hermanvfx.springbackreviewplatform.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserListDto findAllUser(Pageable pageable) {

        List<UserDto> list = userMapper.listUserToListUserDto(userRepository.findAll());

        int last = pageable.getPageNumber() * pageable.getPageSize();
        int first = last - pageable.getPageSize();

        if (list.size() < first) {
            throw new NotFoundException("User not found");
        } else if (list.size() < last) {
            last = list.size();
        }

        Page<UserDto> page = new PageImpl<>(list.subList(first, last), pageable, list.size());

        return new UserListDto()
                .content(list.subList(first, last))
                .totalPages(BigDecimal.valueOf(page.getTotalPages()))
                .totalElements(BigDecimal.valueOf(page.getTotalElements()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public UserDto findUserById(UUID userId) {
        return userMapper.userToUserDTO(userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id[" + userId + "] does not found")));
    }

    @Override
    @Transactional
    public UserDto create(ShortUserDto user) {
        User newUser = userMapper.shortUserDtoToUser(user);
        newUser.setCreate(OffsetDateTime.now());
        return userMapper.userToUserDTO(userRepository.save(newUser));
    }

    @Override
    @Transactional
    public UserDto update(UserDto user, UUID id) {
        return null;
    }

    @Override
    @Transactional
    public void delete(UUID id) {

    }
}
