package com.hermanvfx.springbackreviewplatform.mapper;


import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;
import com.hermanvfx.springbackreviewplatform.entity.Commentary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentaryMapper {

    CommentaryDto commentaryToCommentaryDto(Commentary entity);

    Commentary shortCommentaryDtoToCommentary(ShortCommentaryDto dto);

    Commentary commentaryDtoToCommentary(CommentaryDto dto);
}
