package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.ShortInterviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Interview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                CompanyMapper.class,
                UserMapper.class
        })
public interface InterviewMapper {

    //    После добавления комментариев, удалить!!!
    @Mapping(target = "commentaries", ignore = true)
    InterviewDto interviewToInterviewDto(Interview entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "money", ignore = true)
    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "videoLink", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    Interview shortInterviewDtoToInterview(ShortInterviewDto shortDto);

    //    После добавления комментариев, удалить!!!
    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    Interview interviewDtoToInterview(InterviewDto dto);

    List<InterviewDto> iterableInterviewToListInterviewDto(Iterable<Interview> entities);


}
