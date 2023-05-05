package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.ShortUserDto;
import com.example.userservice.dto.UserDto;
import com.hermanvfx.springbackreviewplatform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                SpecialityMapper.class,
                ReviewMapper.class,
                SpecialityMapper.class
        })

public interface UserMapper {

    //    Попытка избавиться от цикличности:
//    @Mapping(target = "reviewsReceiving", expression = "java(null)")
//    @Mapping(target = "reviewsStudent", expression = "java(null)")
    UserDto userToUserDTO(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specialities", ignore = true)
    @Mapping(target = "reviewsReceiving", ignore = true)
    @Mapping(target = "reviewsStudent", ignore = true)
    @Mapping(target = "socials", ignore = true)
    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "tokens", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User shortUserDtoToUser(ShortUserDto dto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "specialities", ignore = true)
    @Mapping(target = "reviewsReceiving", ignore = true)
    @Mapping(target = "reviewsStudent", ignore = true)
    @Mapping(target = "socials", ignore = true)
    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "tokens", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "role", ignore = true)
    User userDtoToUser(UserDto reviewer);


    List<UserDto> listUserToListUserDto(List<User> entities);


}
