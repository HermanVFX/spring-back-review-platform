package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.CreateSocialDto;
import com.example.userservice.dto.SocialDto;
import com.hermanvfx.springbackreviewplatform.entity.Social;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocialMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    Social socialDtoToSocial(SocialDto dto);

    SocialDto socialToSocialDto(Social entity);

    List<SocialDto> listSocialToListSocialDto (List<Social> entities);

    List<Social> listSocialDtoToListSocial (List<SocialDto> dtos);
}
