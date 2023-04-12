package com.hermanvfx.springbackreviewplatform.mapper;


import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;
import com.hermanvfx.springbackreviewplatform.entity.Commentary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                InterviewMapper.class,
                ReviewMapper.class,
                CommentaryMapper.class,
                CompanyMapper.class
        })
public interface CommentaryMapper {

    CommentaryDto commentaryToCommentaryDto(Commentary entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "dislikes", ignore = true)
    @Mapping(target = "company", expression = "java(null)")
    @Mapping(target = "user", expression = "java(null)")
    @Mapping(target = "interview", expression = "java(null)")
    @Mapping(target = "commentary", expression = "java(null)")
    Commentary shortCommentaryDtoToCommentary(ShortCommentaryDto dto);

    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    Commentary commentaryDtoToCommentary(CommentaryDto dto);
}
