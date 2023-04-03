package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.UserDto;
import com.hermanvfx.springbackreviewplatform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDTO(User entity);

    @Mapping(target = "id", ignore = true)
    User userDtoToUser(UserDto user);

    List<UserDto> listUserToListUserDto(List<User> entities);
}
