package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import com.hermanvfx.springbackreviewplatform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDTO(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "specialities", ignore = true)
    @Mapping(target = "reviewsReceiving", ignore = true)
    @Mapping(target = "reviewsStudent", ignore = true)
    @Mapping(target = "socials", ignore = true)
    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    User shortUserDtoToUser(ShortUserDto dto);

    List<UserDto> listUserToListUserDto(List<User> entities);
}
