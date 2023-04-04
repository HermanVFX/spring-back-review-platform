package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.ShortInterviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Interview;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterviewMapper {
    Interview shortInterviewDtoToInterview(ShortInterviewDto shortDto);
    List<InterviewDto> iterableInterviewToListInterviewDto (Iterable<Interview> entities);
    InterviewDto interviewToInterviewDto (Interview entity);
    Interview interviewDtoToInterview(InterviewDto dto);
}
